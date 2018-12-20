package com.ldy.ragular;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexQuote {
    private static String QUOTE_REGEX = "('|\").*('|\")";

    /**
     * Reference: https://github.com/jirutka/rsql-parser
     * Argument can be a single value, or multiple values in parenthesis separated by comma.
     * Value that doesn’t contain any reserved character or a white space can be unquoted,
     * other arguments must be enclosed in single or double quotes.
     *
     * @param filter
     * @return
     */
    private static String addQuoteToFilter(String filter) {
        if (checkQuote(filter)) {
            return filter;
        } else {
            return "'" + filter + "'";
        }
    }

    private static boolean checkQuote(String filter) {
        Pattern pattern = Pattern.compile(QUOTE_REGEX);
        List<String> list = new ArrayList<>();
        list.add(" a ");
        for (String s:list)
        {
//            s= StringUtils.trimWhitespace(s);
            org.apache.commons.lang3.StringUtils.trim(s);
            System.out.println(StringUtils.trimWhitespace(s));
        }
        Matcher matcher = pattern.matcher(filter);
        return matcher.matches();
    }

    public static void main(String[] args) {
        addQuoteToFilter(" abc ");
    }
}
