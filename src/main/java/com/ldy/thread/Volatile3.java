package com.ldy.thread;

/**
 * Created by yanz3 on 8/30/16.
 */
public class Volatile3 extends Thread {
    private  boolean close = false;
    @Override
    public void run() {
        while (!close) {
            System.out.println("aaaa");
        }
    }

    public void close() throws InterruptedException {
        //Thread.sleep(1);
        close = true;
        System.out.println(close);
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile3 v = new Volatile3();
        v.start();
        v.close();
    }
}
