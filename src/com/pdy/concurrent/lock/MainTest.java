package com.pdy.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter(new MyLock());// 测试不可重入锁
        Counter counter2 = new Counter2(new MyLock2());// 测试可重入锁
        Counter counter3 = new Counter3(new MyReadWriteLock()); // 测试读写锁
        Counter counter4 = new Counter4(new MyReenterReadWriteLock()); // 测试读写锁
        // test(counter1);
        // test(counter2);
        // testReadWrite(counter3);
        testReadWrite(counter4);
    }

    public static void test(Counter counter) throws InterruptedException {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
        newFixedThreadPool.execute(new MyRunnable(counter));
        newFixedThreadPool.execute(new MyRunnable(counter));
        newFixedThreadPool.execute(new MyRunnable(counter));
        newFixedThreadPool.execute(new MyRunnable(counter));
        newFixedThreadPool.shutdown();
        newFixedThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println(counter.getNum());
    }

    /**
     * 两个线程执行写 ，两个线程执行读
     * 
     * @param counter
     * @throws InterruptedException
     */
    public static void testReadWrite(Counter counter) throws InterruptedException {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
        newFixedThreadPool.execute(new MyRunnable4TestReadWriteLock(false, counter));
        newFixedThreadPool.execute(new MyRunnable4TestReadWriteLock(false, counter));
        newFixedThreadPool.execute(new MyRunnable4TestReadWriteLock(true, counter));
        newFixedThreadPool.execute(new MyRunnable4TestReadWriteLock(true, counter));
        newFixedThreadPool.shutdown();
        newFixedThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println(counter.getNum());
    }
}
