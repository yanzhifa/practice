package com.ldy.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ExecutorTest3 {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(() -> {
            try {
                System.out.println("start...");
                Thread.sleep(5000);
                System.out.println("thread end...");
            } catch (Exception ex) {
            }
        });
        service.shutdown();
        service.awaitTermination(4,TimeUnit.SECONDS);
        System.out.println("over....");
    }
}
