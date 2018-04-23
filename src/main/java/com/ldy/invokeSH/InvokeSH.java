package com.ldy.invokeSH;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * Created by yanz3 on 9/22/16.
 */
public class InvokeSH {

    public ScriptResult runLocalScript(String scriptResource, int timeout, ScriptParameters envp,
                                       ScriptParameters parameters, boolean runAsRoot,
                                       String rootUsername, String rootPasswd) throws IOException, ScriptException {
        // Read resource into string, extract shebang command
        InputStream inputStream = getClass().getResourceAsStream(scriptResource);
        // If read from resource stream failed, tried to reload out of class resouce scope
        if (inputStream == null) {
            inputStream = new FileInputStream(scriptResource);
        }
        final String scriptString = IOUtils.toString(inputStream);
        final String shebangCommand = extractShebangCommand(scriptString);

        // Log
        System.out.println(String.format("Running local script: scriptName=%s, shebangCommand=%s, timeout=%d, envp=%s parameters=%s",
                scriptResource, shebangCommand, timeout, envp.toString(), parameters.toString()));

        // See PR : 1498345 for why we have to do this ...
        // Write to temp file
        File tempFile = createTmpFileIdempotent();
        FileUtils.writeStringToFile(tempFile, scriptString);

        // Start process
        final String[] envpActual = envp.getScriptParameters().length == 0 ? null : envp.getPlaintext();
        final String[] command;
        /**
         * Prepare the command based on whether it is executed under root privilege or not.
         *
         * In case of root privilege is needed, the shell command part needs to be flatterned into a string
         * along with any parameters.
         */
        if (runAsRoot) {
            StringBuilder paramsStr = new StringBuilder();
            for (String parameter : parameters.getPlaintextEscaped()) {
                if (StringUtils.isBlank(parameter)) {
                    parameter = "''";
                }
                paramsStr.append(paramsStr.length() == 0 ? parameter : " " + parameter);
            }

            // Need to escape for shell variables and arguments.
            // shebangCommand needs to be limited to sh. It will be python if executing python scripts, which will fail.
            String cmdToExecute = tempFile.getAbsolutePath() + " " + paramsStr;
            String escapedRootPasswd = SimpleScriptParameter.shellEscaper.translate(rootPasswd);
            String shellCmd = "echo '" + escapedRootPasswd + "' | su " + rootUsername + " -c \"" + cmdToExecute + "\"";
            command = new String[]{"sh", "-c", shellCmd};
            if (parameters.hasSensitiveParameter()) {
                System.out.println("To execute command: " + tempFile.getAbsolutePath() + " " + parameters + " under root.");
            } else {
                System.out.println("To execute command: " + cmdToExecute + " under root.");
            }
        } else {
            command = ArrayUtils.addAll(new String[]{shebangCommand, tempFile.getAbsolutePath()}, parameters.getPlaintext());
        }

        final Process process = Runtime.getRuntime().exec(command, envpActual);

        // Start threads to process output and input
        try {
            final ByteArrayOutputStream stdoutOutputStream = new ByteArrayOutputStream();
            final ByteArrayOutputStream stderrOutputStream = new ByteArrayOutputStream();

            final PipeThread stdoutThread = new PipeThread(process.getInputStream(), stdoutOutputStream);
            final PipeThread stderrThread = new PipeThread(process.getErrorStream(), stderrOutputStream);

            process.getOutputStream().close();
            stdoutThread.start();
            stderrThread.start();

            // Wait for process to end
            Integer returnCode = null;
            try {
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < timeout) {
                    try {
                        returnCode = process.exitValue();
                        break;
                    } catch (IllegalThreadStateException e) {
                        Thread.sleep(1000);
                    }
                }

            } catch (InterruptedException e) {
                System.out.println("Interrupted waiting for process");
            }

            // Destroy the process if it didn't end in time.
            if (returnCode == null) {
                System.out.println("Killing script after timeout - taken maximum " + timeout + " ms.");
                process.destroy();
            }

            // Wait for threads to end
            try {
                System.out.println("Joining threads...");
                if (stderrThread.isAlive()) {
                    stderrThread.join();
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted waiting for threads" + e.getLocalizedMessage());
            }

            // Propagate any errors
            if (stdoutThread.getException() != null) {
                System.out.println("stdout errored out" + stdoutThread.getException());
            } else if (stderrThread.getException() != null) {
                System.out.println("stderr errored out" + stderrThread.getException());
            }

            // Return result
            final String stdoutString = new String(stdoutOutputStream.toByteArray());
            final String stderrString = new String(stderrOutputStream.toByteArray());

            if (returnCode == null) {
                System.out.println(String.format("[%s]: Timed out, null return code.", scriptResource));
            } else if (returnCode != 0) {
                System.out.println(String.format("[%s]: Non-zero return code: %d", scriptResource, returnCode));
            }

            if (!stderrString.isEmpty()) {
                System.out.println(String.format("[%s@localhost]: <stderr> %s", scriptResource, stderrString));
            }

            return new ScriptResult(returnCode, stdoutString, stderrString);
        } finally {
            process.destroy();
            tempFile.delete();
        }
    }

    private File createTmpFileIdempotent() throws IOException {
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwx------");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        Path tempFilePath = Files.createTempFile(null, null, attr);
        return new File(tempFilePath.toString());
    }

    private static String extractShebangCommand(String scriptString) throws IllegalArgumentException {
        if (scriptString.length() > 3) {
            if (scriptString.charAt(0) == '#' && scriptString.charAt(1) == '!') {
                final int newlinePosition = scriptString.indexOf('\n');
                if (newlinePosition > 1) {
                    final String unstripped = scriptString.substring(2, newlinePosition);
                    return StringUtils.strip(unstripped);
                }
            }
        }
        throw new IllegalArgumentException("No shebang in script: " + scriptString);
    }

    class PipeThread extends Thread {
        final private InputStream inputStream;
        final private OutputStream outputStream;
        volatile Exception exception;

        public PipeThread(final InputStream inputStream, final OutputStream outputStream) {
            this.inputStream = inputStream;
            this.outputStream = outputStream;
            this.exception = null;
        }

        @Override
        public void run() {
            try {
                final byte buff[] = new byte[1024];
                int nread = this.inputStream.read(buff, 0, 1024);
                while (nread > 0) {
                    this.outputStream.write(buff, 0, nread);
                    nread = this.inputStream.read(buff, 0, 1024);
                }
            } catch (Exception e) {
                this.exception = e;
            } finally {
                IOUtils.closeQuietly(this.inputStream);
                IOUtils.closeQuietly(this.outputStream);
            }
        }

        public Exception getException() {
            return this.exception;
        }
    }

    public static void main(String[] args) throws Exception {
        InvokeSH sh = new InvokeSH();
        ScriptResult result = sh.runLocalScript("/scripts/dig-hostname-ip.sh",
                300000, new ScriptParameters(),
                new ScriptParameters(
//                        ScriptParameter.benign("10.62.91.135"),
//                        ScriptParameter.benign("app32-01.localdomain.local"))
                        ScriptParameter.benign("10.254.174.10"),
                        ScriptParameter.benign("www.baidu.com"))
                , false, null,null);
        if (result.getReturnCode() == null) {
            throw new IllegalStateException(String.format("Script timed out"));
        } else if (result.getReturnCode() != 0) {
            throw new IllegalStateException(String.format("Script returned non-zero result code %d", result.getReturnCode()));
        }
        final String ip = StringUtils.strip(result.getStdout());
        String[] ips = StringUtils.split(ip, "\n");
        for (String s : ips) {
            System.out.println(s);
        }
        if(ip.contains("103.235.46.39")) {
            System.out.println("true");
        }
        if (StringUtils.isNotBlank(ip)) {
            System.out.println(ip);
        }
    }
}
