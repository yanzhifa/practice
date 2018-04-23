package com.ldy.test;

/**
 * Created by yanz3 on 4/17/17.
 */
public class StaticTest {

    static {
        System.out.println("static.....");
    }

    public static void Method1() {
        System.out.println("Method1.....");
    }

    public static void Method2() {
        System.out.println("Method2.....");
    }

    public static void main(String[] args) {
        System.out.println("Hello....");
    }
}
