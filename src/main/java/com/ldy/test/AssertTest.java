package com.ldy.test;

/**
 * Created by yanz3 on 4/2/18.
 */
public class AssertTest {

    public static void main(String[] args) {
        String s = null;
        assert s == null ? true : false;
        assert false;
        System.out.println("end");

    }
}
