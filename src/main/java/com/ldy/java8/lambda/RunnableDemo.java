package com.ldy.java8.lambda;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by yanz3 on 11/8/16.
 */
public class RunnableDemo {
    public static void main(String[] args) {
        final ExecutorService exService = Executors.newSingleThreadExecutor();
        Runnable r = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lambda Expression Test with Runnable");
        };
        exService.execute(r);
        exService.shutdown();

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

        List<String> dbData = new ArrayList<>();//
        dbData.add("1");
        dbData.add("2");
        dbData.add("3");
        dbData.add("4");
        List<String> newData = new ArrayList<>();//
//        newData.add("1");
//        newData.add("5");
        String string = dbData.stream().collect(Collectors.joining(","));
        System.out.println(string);

        dbData.addAll(newData);
//        newData.removeAll(dbData);
        System.out.println(dbData);
        System.out.println(newData);

    }
}