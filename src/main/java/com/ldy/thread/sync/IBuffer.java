package com.ldy.thread.sync;

/**
 * Created by yanz3 on 5/1/18.
 */
public interface IBuffer {
    public void write();
    public void read() throws InterruptedException;
}
