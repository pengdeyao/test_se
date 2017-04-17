package com.pdy.concurrent.lock;

/**
 * 自定义的锁
 * 
 * @author pengdeyao
 *
 */
public interface ILock {

    public void lock() throws InterruptedException;

    public void unLock();
}
