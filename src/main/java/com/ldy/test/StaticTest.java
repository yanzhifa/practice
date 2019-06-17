package com.ldy.test;

/**
 */
public class StaticTest {

    private int a = 1;
    private static int b = 1;

    private static StaticTest test = new StaticTest();

    static {
        System.out.println("static.....");
        b = 200;
    }

    private StaticTest() {
        System.out.println("Constructor...");
        this.a = 10;
        this.b = 10;
    }

    private int sum() {
        return this.a + b;
    }

    public static void main(String[] args) {
        System.out.println(StaticTest.test.sum());
        System.out.println(new StaticTest().sum());
    }
}
