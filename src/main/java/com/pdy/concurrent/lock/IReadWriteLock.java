package com.pdy.concurrent.lock;

/**
 * 自定义的读写锁
 * 
 * @author pengdeyao
 *
 */
public interface IReadWriteLock extends ILock {

    public void lockWrite() throws InterruptedException;

    public void unLockWrite() throws InterruptedException;
}
