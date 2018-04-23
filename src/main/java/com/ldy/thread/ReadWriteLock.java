package com.ldy.thread;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yanz3 on 10/21/16.
 */
public class ReadWriteLock {

    private final Map<String, Semaphore> sshSemaphoreMap = new ConcurrentHashMap<>();

    private final Map<String, java.util.concurrent.locks.ReadWriteLock> sshReadWriteLockMap = new ConcurrentHashMap<>();

    private final static int NUMBER_OF_PERMITS = 5;

    private synchronized Semaphore getSSHSemaphore(String host) {
        if (sshSemaphoreMap.containsKey(host)) {
            return sshSemaphoreMap.get(host);
        } else {
            Semaphore semaphore = new Semaphore(NUMBER_OF_PERMITS);
            sshSemaphoreMap.put(host, semaphore);
            return semaphore;
        }
    }

    private synchronized java.util.concurrent.locks.ReadWriteLock getSSHReadWriteLock(String host) {
        if (sshReadWriteLockMap.containsKey(host)) {
            return sshReadWriteLockMap.get(host);
        } else {
            java.util.concurrent.locks.ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            sshReadWriteLockMap.put(host, readWriteLock);
            return readWriteLock;
        }
    }

    public boolean enableSSH(String host) throws InterruptedException {
        Semaphore semaphore = getSSHSemaphore(host);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            System.out.println("Failed to acquire permit from ssh semaphore");
            return false;
        }
        System.out.println("Thread " + java.lang.Thread.currentThread().toString());

        java.util.concurrent.locks.ReadWriteLock readWriteLock = getSSHReadWriteLock(host);
        readWriteLock.readLock().lock();
        try {
            if (semaphore.availablePermits() == NUMBER_OF_PERMITS - 1) {
                System.out.println("enable SSH before" + java.lang.Thread.currentThread().toString());
                java.lang.Thread.sleep(10000);
                System.out.println("enable SSH after" + java.lang.Thread.currentThread().toString());
                return true;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        System.out.println("SSH connection on host " + host + " was opened by other thread." + java.lang.Thread.currentThread().toString());
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock test = new ReadWriteLock();
        new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.enableSSH("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        java.lang.Thread.sleep(1000);

        new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.enableSSH("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.enableSSH("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
