package com.ldy.thread.sync;

/**
 * Created by yanz3 on 5/1/18.
 */
public class BufferTest {

    public static boolean useSynchronized = false;

    public static void main(String[] args) {
        IBuffer buff = null;
        if (useSynchronized) {
            buff = new Buffer();
        } else {
            buff = new BufferInterruptibly();
        }
        final Reader reader = new Reader(buff);
        final Writer writer = new Writer(buff);
        writer.start();
        reader.start();
        new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                for (; ; ) {
                    // 等5秒钟去中断读
                    if (System.currentTimeMillis() - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        reader.interrupt();
                        break;
                    }

                }

            }
        }).start();
    }
}

class Writer extends Thread {
    private IBuffer buff;

    public Writer(IBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        buff.write();
    }
}

class Reader extends Thread {
    private IBuffer buff;

    public Reader(IBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        try {
            buff.read();
        } catch (InterruptedException e) {
            System.out.println("我不读了");
        }
        System.out.println("读结束");
    }
}
