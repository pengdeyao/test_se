package com.pdy.concurrent.lock;

public interface ILock {

    public void lock() throws InterruptedException;

    public void unLock();
}
