package com.ldy.lock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by yanz3 on 3/13/18.
 */
public class FileLockTest {

    public static void main(String[] args) {

//        WatchService
//        RandomAccessFile
        FileLock lock = null;
        FileChannel channel = null;
        try {

            channel = new FileOutputStream("/Users/yanz3/test/lock.txt", true).getChannel();

            lock = channel.lock();//无参lock()为独占锁
            if(lock == null) {
                System.out.println("Can't get lock.");
            } else {
                System.out.println("Get the lock");
                System.out.println(lock.isShared());
            }
            //lock = channel.lock(0L, channel.size(), true);    //共享锁
            //其它逻辑
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                    lock = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
