package com.ldy.java8.thread;

/**
 * Created by yanz3 on 4/3/18.
 */
public class FutureTest {

    public void className() {
        System.out.println(this.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        FutureTest test = new FutureTest();
        test.className();
    }
}
