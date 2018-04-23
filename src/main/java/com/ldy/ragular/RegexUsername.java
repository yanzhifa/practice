package com.ldy.ragular;

import com.ldy.test.Person;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanz3 on 2/9/17.
 */
public class RegexUsername {
    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String USERNAME_PATTERN1 = "^[a-z_][a-z0-9_-]{2,31}[$]?";
    private static final String USERNAME_PATTERN2 = "^[a-zA-Z][a-zA-Z0-9]{2,31}$";


    public RegexUsername() {
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

    public static void main(String[] args) {


        System.out.println("mk3-4_yong" + " --- " + validate("mk13r2efv24_@---asdv90sdfvjsdj"));
        System.out.println("32" + " --- " + validate("qazwsxedcrfvtgbyhnujmikolpqazwsxq")); //33
        System.out.println("qa" + " --- " + validate("As2df123123456789098765432123456")); //33

        String[] reservedNames = "root,daemon,nfsnobody,dcui,vpxuser".split(",");
        System.out.println(ArrayUtils.contains(reservedNames, "vpxuser"));

     }
}
