package com.ldy.ragular;

import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainReg {
    private Pattern pattern;
    private Matcher matcher;

    private static final String DOMAIN_PATTERN = "^[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$";


    public DomainReg() {
        pattern = Pattern.compile(DOMAIN_PATTERN);
    }

    /**
     * Validate username with regular expression
     *
     * @param domain username for validation
     * @return true valid username, false invalid username
     */
    public static boolean validate(final String domain) {
        Pattern pattern = Pattern.compile(DOMAIN_PATTERN);
        Matcher matcher = pattern.matcher(domain);
        return matcher.matches();

    }

    public static void main(String[] args) {


        System.out.println("mk3-4_yong" + " --- " + validate("asdf.123.1"));
        System.out.println("32" + " --- " + validate("asdfs&*.sdf<<.")); //33
        System.out.println("qa" + " --- " + validate("com.cn")); //33

    }
}
