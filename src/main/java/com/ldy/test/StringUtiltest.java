package com.ldy.test;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yanz3 on 8/16/16.
 */
public class StringUtiltest {

    public static void main(String[] args) {
        String s = "34,hello";
        System.out.println( StringUtils.substring(s, s.indexOf(",")+1));
    }
}
