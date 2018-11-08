package com.ldy.test;


public class Calculate {

    public static void main(String[] args) {
        test();
    }

    private static void test(){

        int i = Math.max(cal(2),cal(3)) + Math.max(cal(4),cal(5));
        System.out.println(i);
    }

    private static int cal(int input) {
        return input + 1;
    }
}
