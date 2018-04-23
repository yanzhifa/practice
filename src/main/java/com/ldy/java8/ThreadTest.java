package com.ldy.java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yanz3 on 10/18/17.
 */
public class ThreadTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("thread..." + Thread.currentThread().getName());

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();

    }
}
