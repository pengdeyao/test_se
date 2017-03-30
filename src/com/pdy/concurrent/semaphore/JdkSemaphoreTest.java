package com.pdy.concurrent.semaphore;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class JdkSemaphoreTest {


    public static void main(String[] args) {
        // 一个计数信号量，可以指定初始许可数量，可以阻塞每一个aquire()
        // 非synchronized 轻量级同步实现锁，基于cpu cas指令完成。比锁竞争更效率(锁有加锁、释放操作)，能实现公平锁。提高速度。
        Semaphore semaphore = new Semaphore(1);// 非公平的信号量
        semaphore = new Semaphore(1, true);// 公平信号量，队列实现公平.
        Executor es = Executors.newCachedThreadPool();
        es.execute(new SenderThread(semaphore));
        es.execute(new SenderThread(semaphore));
        // es.execute(new ReciverThread(semaphore));
        // es.execute(new ReciverThread(semaphore));
        // es.execute(new ReciverThread(semaphore));

    }
}
