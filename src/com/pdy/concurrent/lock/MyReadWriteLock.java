package com.pdy.concurrent.lock;
/**<PRE>
 * 读写锁实现<BR> 
 * 需求：
 *      1. 读锁不需要强制同步，多个线程都可以读取共享资源
 *      2. 当需要获取写锁时，再想要读取共享资源的读线程应该被阻塞，直到写锁执行完成。
 *      3. 当存在已经获取写锁的线程，且想要继续获取写锁时，线程应该阻塞并等待上一个写锁完成。此时仍然不允许获取读锁 
 * @author pengdeyao
 *
 */
public class MyReadWriteLock implements IReadWriteLock {
    /** 已经或读锁的线程数 */
    private int readers = 0;
    /** 想要获取写锁的线程数 */
    private int requestWrite = 0;
    /** 已经获取写锁的线程数 */
    private int writers = 0;

    @Override
    public synchronized void lock() throws InterruptedException {
        // 当有需求写锁或者已经存在写锁时，不应该再允许读
        while (requestWrite > 0 || writers > 0) {
            this.wait();
        }
        // 读锁成功 +1
        readers++;

    }

    @Override
    public synchronized void unLock() {
        readers--;
        notifyAll();// 可唤醒等待读完的写线程
    }

    public synchronized void lockWrite() throws InterruptedException {
        requestWrite++;
        while (readers > 0 || writers > 0) { // 如果已经有读的线程，需要等待读线程执行完成。
            this.wait();
        }
        writers++;
    }

    public synchronized void unLockWrite() {
        requestWrite--;
        writers--;
        this.notifyAll();
    }
}
