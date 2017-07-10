package com.pdy.concurrent.lock;

/**
 * 参照 http://ifeve.com/java-concurrency-thread-directory/ 代码实践<BR>
 * 普通锁<BR>
 * 不可重入锁
 * 
 * 
 * @author pengdeyao
 *
 */
public class MyLock implements ILock {

    private boolean isLocked = false;// 标记是否已经被一个线程锁定，如果已经被锁定，其他线程想要加锁的时候需要等待.

    /**
     * 获取锁，如果获取不到则等待
     * 
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            // if (isLocked) { 此处如果用if 存在假唤醒的问题. 自旋锁解决
            this.wait();
            if (isLocked) {
                System.out.println(Thread.currentThread().getName() + "假唤醒");
            }
        }
        isLocked = true;
    }

    /**
     * 释放锁，并通知等待的线程
     */
    public synchronized void unLock() {
        isLocked = false;
        this.notify();
    }

}
