package com.ldy.extend;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.regex.Pattern;

/**
 * Created by yanz3 on 3/20/17.
 */
public class MainClass {

    private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$");

    public static void main(String[] args) {

        Apple apple = new Apple("Red", 6.5, "Apple");
        System.out.println(apple);

        Fruit fruit = new Fruit("Common", 10.0);
        System.out.println(fruit);

        System.out.println(isValidFqdnHostname("abc.cmd"));

    }

    public static boolean isValidFqdnHostname(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }

        int hostIndex = s.indexOf('.');
        if (hostIndex == -1) {
            return false;
        }
        if (!isValidHostname(s.substring(0, hostIndex))) {
            return false;
        }
        String domainName = s.substring(hostIndex + 1);
        // We allow local domains so we can't use the Apache lib here.
        if (!isValidHostname(domainName)){
            return false;
        }
        return true;
    }

    public static boolean isValidHostname(String s) {
        if (s == null) return false;
        return s.length() <= 255 && HOSTNAME_PATTERN.matcher(s).matches();
    }


}

