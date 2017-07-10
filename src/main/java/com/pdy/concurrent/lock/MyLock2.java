package com.pdy.concurrent.lock;

/**
 * 参照 http://ifeve.com/java-concurrency-thread-directory/ 代码实践<BR>
 * 可重入锁<BR>
 * 
 * @author pengdeyao
 *
 */
public class MyLock2 implements ILock {
    /** 标记是否已经被一个线程锁定，如果已经被锁定，其他线程想要加锁的时候需要等待. */
    private boolean isLocked = false;
    /** 锁定的线程 */
    private Thread lockedBy = null;
    /** 重入加锁次数 */
    private int lockCount = 0;

    /**
     * 获取锁，如果获取不到则等待
     * 
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        Thread currThread = Thread.currentThread();
        while (isLocked && currThread != lockedBy) {
            // if (isLocked) { 此处如果用if 存在假唤醒的问题. 自旋锁解决
            this.wait();
            if (isLocked) {
                System.out.println(Thread.currentThread().getName() + "假唤醒");
            }
        }
        this.lockedBy = Thread.currentThread();
        lockCount++;
        isLocked = true;
    }

    /**
     * 释放锁，并通知等待的线程
     */
    public synchronized void unLock() {
        // 如果是当前线程，解锁则-- ，直到为0 就释放并通知
        if (Thread.currentThread() == this.lockedBy) {
            lockCount--;
            if (lockCount == 0) {
                isLocked = false;
                this.notify();
            }
        }
    }

}
