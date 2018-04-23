package com.ldy.ragular;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanz3 on 8/18/16.
 */
public class RagularTest {

    //private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$");
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])$");

    public static void main(String[] args) {
        String str = "abcdef";
        System.out.println(str.substring(0,str.length()-1));

        String s = "1asdf-_a";
        System.out.println(HOSTNAME_PATTERN.matcher(s).matches());

        Boolean a = false;
        String ss = "asdf";
        boolean b = true;

        if(1==1) {
            System.out.println("asdf");
        }
        System.out.println(checkSpecialCharactersRequiredViolated("Vmware"));

        System.out.println("\\");
    }

    /**
     * Regex to determine if a leaf domain is valid.
     */
    private static final Pattern SHORT_HOSTNAME_PATTERN = Pattern.compile("^([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])$");


    /**
     * Check for a valid hostname without domain
     */
    public static boolean isValidShortHostname(String s) {
        if (s == null) return false;
        return s.length() <= 64 && SHORT_HOSTNAME_PATTERN.matcher(s).matches();
    }


    public static boolean checkSpecialCharactersRequiredViolated(String password) {
//        String specialCharacterRegex = "[!@#$%&^*\\|\\-\\~\\_\\:\\.\\,\\[\\]()+\\{\\}]";
        //String specialCharacterRegex = "[`!@#$%^&*=<>?/;'\"|\\-\\~\\_\\:\\.\\,\\[\\]()+\\{\\}]";
        String specialCharacterRegex = "[\\p{Punct}|\\s]";
        // The ability to require the use of special characters such as !, @, #, $, %, &, ^ and *.
        final Pattern specialCharacterPattern = Pattern.compile(specialCharacterRegex);
        System.out.println(specialCharacterPattern.pattern());

        final Matcher specialCharacterMatcher1 = specialCharacterPattern.matcher(password);
        System.out.println(specialCharacterMatcher1.find());

        String result = specialCharacterRegex.replaceAll("\\\\", "");//.replaceAll("(.)", "$1 ");
        System.out.println(result);
        System.out.println(StringUtils.substring(specialCharacterRegex.replaceAll("\\\\", ""), 1, result.length()-1).replaceAll("(.)", "$1 "));
        final Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);
        final boolean specialCharacterFound = specialCharacterMatcher.find();

        return specialCharacterFound;

    }
}
