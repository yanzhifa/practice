package com.ldy.invokeSH;

/**
 * Result of running a script.
 */
public class ScriptResult {

    /**
     * Return code from the script.
     * If the script session abruptly terminates, this would be null.
     */
    private final Integer returnCode;

    /**
     * What the script wrote to stdout.
     */
    private final String stdout;

    /**
     * What the script wrote to stderr.
     */
    private final String stderr;

    public ScriptResult(Integer returnCode, String stdout, String stderr) {
        this.returnCode = returnCode;
        this.stdout = stdout;
        this.stderr = stderr;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public String getStdout() {
        return stdout;
    }

    public String getStderr() {
        return stderr;
    }
}
