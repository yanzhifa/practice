package test;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanz3 on 9/13/16.
 */
public class EsxcliTest {
    public static void main(String[] args) {

        try {

            //String[] cmd = new String[]{"/bin/sh", "-c", "ls"};
            /*String[] cmd = EMCUtil.getSSHCommand("10.62.81.203", "mystic", "mystic", "ls");
            Process ps = Runtime.getRuntime().exec(cmd);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();

            System.out.println(result);

            String[] cmdarray = {"sshpass", "-p", "'password'", "ssh", "management@10.62.91.132", "whoami"};*/
            //Process p = Runtime.getRuntime().exec(cmdarray);
            //p.waitFor();
            //p.getOutputStream().toString();

            //String addCommand = "/tmp/vspexblue/bin/sas3ircu 0 display";

            String command = "esxcli storage nmp satp rule list|grep 'VMW_SATP_LOCAL' | grep naa.5000cca04f238d78";
            Process process = Runtime.getRuntime().exec(EMCUtil.getSSHCommand("10.62.91.132", "management", "osgqvtubqhlma1es6Lh!", command));
            if (!process.waitFor(5, TimeUnit.SECONDS)) {
                System.out.println("aaaaaaa");
            }
            BufferedReader brd = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb1 = new StringBuffer();
            String a;
            while ((a = brd.readLine()) != null) {
                sb1.append(a).append("\n");
            }
            if(StringUtils.isNotEmpty(sb1)) {
                System.out.println("kkkkkkkkk");
            }

            String addCommand = "esxcli storage nmp satp rule add -s VMW_SATP_LOCAL -o \"enable_local\" -d naa.5000cca04faded94";
            Process addProcess = Runtime.getRuntime().exec(EMCUtil.getSSHCommand("10.62.91.132", "management", "osgqvtubqhlma1es6Lh!", addCommand));
            if (!addProcess.waitFor(5, TimeUnit.SECONDS)) {
                System.out.println("aaaaaaa");
            }

            if(addProcess.exitValue() != 0) {
                BufferedReader br = new BufferedReader(new InputStreamReader(addProcess.getErrorStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String result = sb.toString();
                System.out.println(result);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(addProcess.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);

            String claimCommand = "esxcli storage core claiming reclaim -d naa.5000cca04faded94";
            Process claimProcess = Runtime.getRuntime().exec(EMCUtil.getSSHCommand("10.62.91.132", "management", "osgqvtubqhlma1es6Lh!", claimCommand));
            if (!claimProcess.waitFor(5, TimeUnit.SECONDS)) {
                System.out.println("bbbbbbbb");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("ok");
        }
    }
}
