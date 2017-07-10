package com.pdy.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class ReciverThread implements Runnable {
    private Semaphore semaphore = null;

    public ReciverThread(Semaphore semaphore) {
        super();
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获取一个资源");
        }

    }

}
