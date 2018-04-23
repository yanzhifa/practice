package com.ldy.thread;

/**
 * Created by yanz3 on 11/21/16.
 */
public class ThreadTest1 {

    public static void threadMethod() {
        System.out.println("Start...");
        new Thread() {

            @Override
            public void run() {
                printMsg();
            }

        }.start();

        System.out.println("finish...");
    }

    static public void printMsg() {
        System.out.println("hello..." + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        threadMethod();
        printMsg();
    }
}
