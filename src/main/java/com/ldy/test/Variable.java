package com.ldy.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yanz3 on 10/24/16.
 */
public class Variable {
    public static void main(String[] args) {
        String a = "ABC";
        String b = a;
        a = "XYZ";
        System.out.println(b);

        AtomicInteger integer = new AtomicInteger(0);
        System.out.println(integer.incrementAndGet());

        System.out.println(integer.getAndDecrement());
        System.out.println(integer.get());
    }
}
