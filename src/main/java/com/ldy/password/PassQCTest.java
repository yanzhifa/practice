package com.ldy.password;

/**
 * Created by yanz3 on 5/2/18.
 */
public class PassQCTest {

    public static void main(String[] args) {
        Passwd  passwd = new Passwd("mystic",",,,");
        PasswdQcParams passwdQcParams = new PasswdQcParams();

        System.out.println(PasswdQcCheck.passwdQcCheck(passwdQcParams, "VaVaVa11!", "Passw0rd!", passwd));
    }
}
