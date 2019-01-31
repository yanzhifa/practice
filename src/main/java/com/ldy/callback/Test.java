package com.ldy.callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    static ExecutorService es = Executors.newFixedThreadPool(2);

    public static void doSth(Callable callback) {

        es.execute(()->{
            System.out.println("do sth...");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Map<String, String > map = new HashMap<>();
            map.put("aa","test");
            try {
                callback.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        System.out.println("start");

        doSth(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("over: ");
                return null;
            }
        });


        System.out.println("finished");
    }
}
