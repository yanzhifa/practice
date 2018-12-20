package com.ldy.ragular;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanz3 on 2/9/17.
 */
public class RegexPassword {
    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String USERNAME_PATTERN1 = "^[a-z_][a-z0-9_-]{2,31}[$]?";
    private static final String USERNAME_PATTERN2 = "^[a-zA-Z][a-zA-Z0-9]{2,31}$";
    private static final String QUOTE = "^['|\"](.*)['|\"]$";


    public RegexPassword() {
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    /**
     * Validate username with regular expression
     *
     * @param username username for validation
     * @return true valid username, false invalid username
     */
    public static boolean validate(final String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN2);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();

    }

    public static boolean checkForRepeatedCharacterViolation(String password) {
        int maxRepeatedCharacters = 3;
        if (maxRepeatedCharacters > 1) {
            String maxRepeatedRegex = String.format("(.)\\1{%s,}", maxRepeatedCharacters);
            Pattern maxRepeatedCharactersPattern = Pattern.compile(maxRepeatedRegex);
            Matcher maxRepeatedMatcher = maxRepeatedCharactersPattern.matcher(password);
            return maxRepeatedMatcher.find();
        } else{
            return false;
        }
    }

    public static boolean checkForbiddenPasswordFound(String password) {
        // The ability to prevent the use of a set of banned passwords (currently admin, root and vmware)
        final Pattern forbiddenPasswordPattern = Pattern.compile("");
        final Matcher forbiddenPasswordMatcher = forbiddenPasswordPattern.matcher(password);
        boolean forbiddenPasswordFound = forbiddenPasswordMatcher.find();
        return forbiddenPasswordFound;
    }

    public static void main(String[] args) {


//        System.out.println("mk3-4_yong" + " --- " + validate("mk13r2efv24_@---asdv90sdfvjsdj"));
//        System.out.println("32" + " --- " + validate("qazwsxedcrfvtgbyhnujmikolpqazwsxq")); //33
//        System.out.println("qa" + " --- " + validate("As2df123123456789098765432123456")); //33
//
//        String[] reservedNames = "root,daemon,nfsnobody,dcui,vpxuser".split(",");
//        System.out.println(ArrayUtils.contains(reservedNames, "vpxuser"));

        //System.out.println(checkForRepeatedCharacterViolation("tessstssss"));


        System.out.println("*asdf'as*".matches("(\\*).*(\\*)"));

        System.out.println(StringUtils.startsWith("\"str","\""));
     }
}
