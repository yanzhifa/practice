package com.ldy.thread;

import com.ldy.test.Person;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest2 {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        executorService.execute(new Runnable() {
//            public void run() {
//                System.out.println("Asynchronous task");
//            }
//        });
//
//        executorService.shutdown();

        List<String> list = Arrays.asList("AAAA", "BBBB", "CCCC");

        final Map<String, String> marvinVibVersions = new ConcurrentHashMap<>();

        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (final String name : list) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("In thread " + Thread.currentThread().getName());
                        Thread.sleep(2000);
                        marvinVibVersions.put(name, Thread.currentThread().getName());
                    } catch (Exception e) {

                    }
                }
            });
        }
        executorService.shutdown();
        try {
            //timeout in 30 mins
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
        }

        System.out.println(marvinVibVersions.values());

        Map<Person,String> map = new HashMap<>();
        Person p1 = new Person("P1", 10);
        Person p2 = new Person("P2", 15);
        map.put(p1, "Person1");
        map.put(p2, "Person2");



        map.entrySet().forEach(personStringEntry -> {
            System.out.println(personStringEntry.getKey().getName() + ":" + personStringEntry.getValue());
        });

    }

}
