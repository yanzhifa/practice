package com.ldy.java8.lambda;

/**
 * Created by yanz3 on 11/8/16.
 */
public class CalculatorDemo {
    public static void main(String[] args) {
        Calculator cal = (int a, int b) -> a + b;
        int res = cal.add(5, 6);
        System.out.println(res);
    }
}
