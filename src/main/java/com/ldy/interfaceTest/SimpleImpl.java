package com.ldy.interfaceTest;

/**
 * Created by yanz3 on 9/13/16.
 */
public class SimpleImpl implements Simple {
    @Override
    public void sayHello() {
        System.out.println("hello....");
    }

    public static void main(String[] args) {
        Simple simple = new SimpleImpl();
        simple.sayHello();

        System.out.println(simple.getClass().getSimpleName());
    }
}
