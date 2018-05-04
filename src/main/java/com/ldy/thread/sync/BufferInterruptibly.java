package com.ldy.thread.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanz3 on 5/1/18.
 */
public class BufferInterruptibly  implements IBuffer{

    private ReentrantLock lock = new ReentrantLock();

    public void write() {
        //lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            //System.out.println("开始往这个buff写入数据…");
            //for (;;)// 模拟要处理很长时间
            //{
            //    if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE)
            //        break;
            //}
            writeContent();
            //Thread.sleep(2000);
            System.out.println("终于写完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //lock.unlock();
        }
    }

    public void read() throws InterruptedException{
        //System.out.println("pending read.");
        lock.lockInterruptibly();// 注意这里，可以响应中断
        try {
            readOut();
            //System.out.println("从这个buff读数据");
        } finally {
            //lock.unlock();
        }
    }

    private synchronized static void readOut() {
        System.out.println("Read.......");
    }

    private synchronized static void writeContent() throws InterruptedException {
        System.out.println("Writing.......");
        Thread.sleep(2000);
    }

}
