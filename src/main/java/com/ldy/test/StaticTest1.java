package com.ldy.test;

import org.apache.commons.lang3.StringUtils;

import static com.ldy.test.StaticTest.Method1;
import static com.ldy.test.StaticTest.Method2;

/**
 * Created by yanz3 on 4/17/17.
 */
public class StaticTest1 {


    public static void main(String[] args) {
        Method1();
        Method2();

        System.out.println(StringUtils.equals(null, null));
    }
}
