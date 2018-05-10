package com.ldy.thread;

import com.ldy.java8.ThreadTest;

/**
 * Created by yanz3 on 5/9/18.
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new JoinTest());

        //t1.start();
        //t1.join();

        System.out.println("end..");

        int i = 0;
        int j = i++;
        int k = ++i;
        System.out.println(j);
        System.out.println(k);
        System.out.println(12%10);
        System.out.println(12/10);
        System.out.println(Integer.MAX_VALUE);
    }
}

class JoinTest implements Runnable {

    @Override
    public void run() {
        System.out.println("start run");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run finish");
    }
}
