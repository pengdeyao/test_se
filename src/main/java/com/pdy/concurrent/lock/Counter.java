package com.pdy.concurrent.lock;

/**
 * 计数器<BR>
 * 保证多线程下的并发安全<BR>
 * 
 * @author pengdeyao
 *
 */
public class Counter {

    protected ILock myLock = null;
    protected int num = 0;

    public Counter(ILock myLoock) {
        super();
        this.myLock = myLoock;
    }

    public void incr() throws InterruptedException {
        myLock.lock();
        num++;
        myLock.unLock();
    }

    public int getNum() throws InterruptedException {
        return num;
    }
}

class Counter2 extends Counter {

    public Counter2(ILock myLoock) {
        super(myLoock);
    }

    public void incr() throws InterruptedException {
        super.myLock.lock();
        super.num++;
        super.myLock.lock();
        super.myLock.lock();
        super.myLock.unLock();
        super.myLock.unLock();
        super.myLock.unLock();
    }

    public int getNum() {
        return num;
    }

}

/**
 * 测试读写锁
 * 
 * @author pengdeyao
 *
 */
class Counter3 extends Counter {

    public Counter3(ILock myLoock) {
        super(myLoock);
    }

    public void incr() throws InterruptedException {
        // 打乱线程执行加锁的时机，提高错开获取写锁的概率
        IReadWriteLock myReadWriteLock = ((IReadWriteLock) super.myLock);
        if (System.currentTimeMillis() % 2 == 0) {
            Thread.currentThread().sleep(1000);
        } else {
            Thread.currentThread().sleep(8000);
        }
        myReadWriteLock.lockWrite();
        System.out.println(Thread.currentThread().getName() + "执行写入操作");
        Thread.sleep(2000);
        super.num++;
        myReadWriteLock.unLockWrite();
        Thread.sleep(1000);
    }


    public int getNum() throws InterruptedException {
        IReadWriteLock myReadWriteLock = ((IReadWriteLock) super.myLock);
        myReadWriteLock.lock();
        int result = num;
        System.out.println(Thread.currentThread().getName() + "读取数据" + result);
        myReadWriteLock.unLock();
        return result;
    }

}

/**
 * 测试读可重入的读写锁
 * 
 * @author pengdeyao
 *
 */
class Counter4 extends Counter {

    public Counter4(IReadWriteLock myLoock) {
        super(myLoock);
    }

    public void incr() throws InterruptedException {
        // 打乱线程执行加锁的时机，提高错开获取写锁的概率
        IReadWriteLock myReadWriteLock = ((IReadWriteLock) super.myLock);
        if (System.currentTimeMillis() % 2 == 0) {
            Thread.currentThread().sleep(3000);
        } else {
            Thread.currentThread().sleep(10000);
        }
        myReadWriteLock.lockWrite();
        myReadWriteLock.lockWrite();
        System.out.println(Thread.currentThread().getName() + "执行写入操作");
        Thread.sleep(1000);
        super.num++;
        myReadWriteLock.unLockWrite();
        myReadWriteLock.unLockWrite();
        Thread.currentThread().sleep(3000);
    }


    public int getNum() throws InterruptedException {
        IReadWriteLock myReadWriteLock = ((IReadWriteLock) super.myLock);
        myReadWriteLock.lock();
        myReadWriteLock.lock();
        myReadWriteLock.lock();
        int result = num;
        System.out.println(Thread.currentThread().getName() + "读取数据" + result);
        myReadWriteLock.unLock();
        myReadWriteLock.unLock();
        myReadWriteLock.unLock();
        return result;
    }

}
