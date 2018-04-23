package com.ldy.SSH;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanz3 on 12/23/16.
 */
public class SSHCommand {

    public static String[] getSSHCommand(String host, String username, String password, String command) {
        String target = username + "@" + host;
        // In Bash, ' (single quote) doesn't accept any interpret of special characters;
        // The only thing cannot be put into a a pair of ' (s) is ' itself;
        // Thus all ' (s) must be wrapped with a pair of " (double quote);
        // Each section splitted by ' (s) must be wrapped with a pair of ' (single quote);
        // step 1: replace all ' (s) with '"'"' (sdsds)
        // step 2: quote the whole string with a pair of ' (s)
        String escapedTarget = target.replace("'", "'\"'\"'");
        String escapedPassword = password.replace("'", "'\"'\"'");
        String escapedCommand = command.replace("'", "'\"'\"'");

        String remoteCommand = "sshpass -p '" + escapedPassword
                + "' ssh -o StrictHostKeychecking=no -o UserKnownHostsFile=/dev/null '" + escapedTarget + "' '"
                + escapedCommand + "'";

        return new String[] { "bash", "-c", remoteCommand };
    }

    public static void main(String[] args) {

        String command = "esxcli storage nmp satp rule list | grep 'VMW_SATP_LOCAL' | grep " + "naa.5000cca04e1f6c88";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(getSSHCommand("10.62.92.74", "root", "Testesx123!", command));
            if (!process.waitFor(5, TimeUnit.SECONDS)) {
                String errorMsg = "Check disk if make as local failed.";
                System.out.println(errorMsg);
                //throw new RuntimeException(errorMsg);
            }
            System.out.println(process.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
