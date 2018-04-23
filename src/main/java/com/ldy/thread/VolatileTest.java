package com.ldy.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class VolatileTest {
//    AtomicBoolean flag = new AtomicBoolean(false);
    volatile boolean flag = false;
//    boolean flag = false;
    void loop () {
        while(!flag) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("running.......");
        }
    }
    void stop () {
//        flag.compareAndSet(false, true);
        flag = true;
    }
    
    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.loop();
            }
        }).start();
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                test.stop();
            }
        }).start();
    }
}
