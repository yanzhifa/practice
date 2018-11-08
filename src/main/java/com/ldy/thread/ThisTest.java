package com.ldy.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThisTest {

    A a1 = new A();
    A a2 = new A();

    AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {

        ThisTest t = new ThisTest();
        t.test();
    }

    public void test() throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a1.print("I am a1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a2.print("I am a2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    class A {
        public void print(String str) throws InterruptedException {
            if(atomicBoolean.compareAndSet(false, true)) {
                System.out.println(str);
                Thread.sleep(2000);
            }

            atomicBoolean.set(false);

        }
    }
}
