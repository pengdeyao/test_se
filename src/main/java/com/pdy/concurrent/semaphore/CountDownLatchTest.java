package com.pdy.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;

/**
 * <PRE>
 * 倒数栓测试<BR>
 * CountDownLatch <BR>
 * 作用: 能使一个线程等他其他线程执行完成后再执行。<BR>
 * 实现: 通过一个计数器来实现的，每个线程完成任务就调用countDown 将计数器减一，当计数器为0时代表所有的线程执行完成。等待的线程恢复执行。
 * 使用场景: 系统执行启动前，等待基础服务检查完毕才能启动。
 * 
 * 
 * @author pengdeyao
 *
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread thread1 = new Thread(new Worker(countDownLatch));
        Thread thread2 = new Thread(new Worker(countDownLatch));
        Thread thread3 = new Thread(new Worker(countDownLatch));
        thread1.start();
        thread2.start();
        thread3.start();
        countDownLatch.await();// 等待线程都执行完成
        System.out.println(Thread.currentThread().getName() + "主线程执行完毕");
    }

}

class Worker implements Runnable {

    private CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch) {
        super();
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "任务开始执行完成");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "任务执行完成");
            // 任务执行完成，倒数.
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
