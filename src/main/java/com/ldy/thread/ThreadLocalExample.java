package com.ldy.thread;

import java.util.Random;

/**
 * Created by yanz3 on 5/8/18.
 */
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
                new ThreadLocal<Integer>();

        private int intValue = 0;

        private Random random = new Random();

        @Override
        public void run() {

            threadLocal.set((int) (Math.random() * 100D));
            intValue = random.nextInt(10);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(Thread.currentThread() + ":" + threadLocal.get() + ", IntValue:" + intValue);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}
