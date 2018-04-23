package com.ldy.thread;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VolatileTest1 {
    private static final Logger LOGGER = Logger.getLogger(VolatileTest1.class.getName());

    //private static volatile int MY_INT = 0;
    private static int MY_INT = 0;
    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            //int local_value = MY_INT;
            int local_value = 0;
            while (local_value < 5) {
                //String s = local_value + " = " + MY_INT;
                //System.out.println(local_value + ":" + MY_INT);
//                if (local_value != MY_INT) {
//                    // LOGGER.log(Level.INFO, "Got Change for MY_INT : {0}",
//                    // MY_INT);
//                    System.out.println("Got Change for MY_INT " + MY_INT);
//                    local_value = MY_INT;
//                }
                if(!flag.get()) {
                    System.out.println("Got Change for MY_INT " + local_value);
                    local_value += 1;
                    flag.getAndSet(true);
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {

//            int local_value = MY_INT;
//            while (MY_INT < 5) {
//                System.out.println("Incrementing MY_INT to " + MY_INT);
//                // LOGGER.log(Level.INFO, "Incrementing MY_INT to {0}",
//                // local_value + 1);
//                MY_INT = ++local_value;
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            
            int local_value = 0;
            while (local_value < 5) {
                System.out.println("Incrementing MY_INT to " + local_value);
                // LOGGER.log(Level.INFO, "Incrementing MY_INT to {0}",
                // local_value + 1);
                ++local_value;
                try {
                    flag.getAndSet(false);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
