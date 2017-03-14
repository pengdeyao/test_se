package com.pdy.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter(new MyLock());// 测试不可重入锁
        Counter counter2 = new Counter2(new MyLock2());// 测试可重入锁
        // test(counter1);
        test(counter2);
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

}
