package com.pdy.concurrent.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * <PRE>
 * 读写锁实现<BR>
 * 需求： 1. 读锁不需要强制同步，多个线程都可以读取共享资源 2. 当需要获取写锁时，再想要读取共享资源的读线程应该被阻塞，直到写锁执行完成。 3.
 * 当存在已经获取写锁的线程，且想要继续获取写锁时，线程应该阻塞并等待上一个写锁完成。此时仍然不允许获取读锁 需求: 1. 可重入的读写锁。
 * （获取读锁的线程可以继续获取读锁，不被限制等待，获取写锁的线程和继续获取写锁） 实现思路： 1. 读锁的可重入： 保存获取读锁的线程到map
 * ，记录加锁次数，当解锁为0时才算此次同步区域执行完成。 2. 写锁的可重入:
 * 由于一次只能保持一个写锁，所以只需要记录当前获取锁的线程，并记录加锁次数。当解锁到0时，才代表同步区域执行完成。
 * 
 * @author pengdeyao
 *
 */
public class MyReenterReadWriteLock implements IReadWriteLock {
    /** 已经或读锁的线程数 */
    private int readers = 0;
    /** 想要获取写锁的线程数 */
    private int requestWrite = 0;
    /** 已经获取写锁的线程数 */
    private int writers = 0;
    /** 存放已经获取读锁的线程 value= 重入次数 */
    private Map<Thread, Integer> readLockThreadMap = new HashMap<Thread, Integer>();
    /** 当前获取写锁的线程 */
    private Thread writeLockThread = null;


    @Override
    public synchronized void lock() throws InterruptedException {
        Thread currThread = Thread.currentThread();
        boolean isHaveLock = readLockThreadMap.containsKey(currThread);
        // 当有需求写锁或者已经存在写锁时，不应该再允许读
        while ((requestWrite > 0 || writers > 0) && !isHaveLock) {
            this.wait();
        }
        if (isHaveLock) {
            Integer lockCount = readLockThreadMap.get(currThread);
            readLockThreadMap.put(currThread, ++lockCount);
        } else {
            // 读锁成功 +1
            readers++;
            readLockThreadMap.put(currThread, 1);
        }

    }

    @Override
    public synchronized void unLock() {
        Thread currThread = Thread.currentThread();
        Integer lockCount = readLockThreadMap.get(currThread);
        lockCount--;
        if (lockCount <= 0) { // 重入锁执行完毕
            readers--;
            readLockThreadMap.remove(currThread); // 移除当前线程
            notifyAll();// 可唤醒等待读完的写线程
        } else {
            readLockThreadMap.put(currThread, lockCount);
        }

    }

    public synchronized void lockWrite() throws InterruptedException {
        requestWrite++;
        Thread currThread = Thread.currentThread();
        System.out.println(currThread.getName() + "请求写锁 当前线程" + writeLockThread);
        boolean isCurrLock = writeLockThread == currThread;
        while ((readers > 0 || writers > 0) && writeLockThread != null && !isCurrLock) { // 如果已经有读的线程，需要等待读线程执行完成。
            this.wait();
        }
        if (writeLockThread == null) {
            writeLockThread = currThread;
        }
        writers++;
    }

    public synchronized void unLockWrite() {
        Thread currThread = Thread.currentThread();
        boolean isCurrLock = writeLockThread == currThread;
        if (isCurrLock) {
            requestWrite--;
            writers--;
            System.out.println(currThread.getName() + "解锁, 请求写数量" + requestWrite);
            if (writers <= 0) {
                writeLockThread = null;
                notifyAll();
            }
        }
    }

}
