package com.pdy.concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JdkReadWriteLock implements IReadWriteLock {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    @Override
    public void lock() throws InterruptedException {
        readWriteLock.readLock().lock();
    }

    @Override
    public void unLock() {
        readWriteLock.readLock().unlock();
    }

    @Override
    public void lockWrite() throws InterruptedException {
        readWriteLock.writeLock().lock();
    }

    @Override
    public void unLockWrite() throws InterruptedException {
        readWriteLock.writeLock().unlock();
    }

}
