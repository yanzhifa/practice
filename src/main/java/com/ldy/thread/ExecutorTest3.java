package com.ldy.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ExecutorTest3 {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future future = service.submit(() -> {
            try {
                System.out.println("start...");
                Thread.sleep(5000);
                System.out.println("thread end...");
            } catch (Exception ex) {
            }
        });
        //service.shutdown();
        System.out.println(service.isTerminated());
        service.awaitTermination(10,TimeUnit.SECONDS);
        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(service.isTerminated());
        System.out.println("over....");
    }
}
