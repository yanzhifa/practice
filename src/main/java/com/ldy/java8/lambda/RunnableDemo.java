package com.ldy.java8.lambda;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yanz3 on 11/8/16.
 */
public class RunnableDemo {
    public static void main(String[] args) {
        final ExecutorService exService = Executors.newSingleThreadExecutor();
        Runnable r = () -> System.out.println("Lambda Expression Test with Runnable");
        exService.execute(r);

        Callable runnable = () -> {
            Thread.sleep(1000);
            System.out.println("You hit it!");

            // s

            return null;
        };

        try {
            System.out.println("start");
            runnable.call();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}