package com.pdy.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class SenderThread implements Runnable {

    private Semaphore semaphore = null;



    public SenderThread(Semaphore semaphore) {
        super();
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (;;) {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + "释放一个资源到信号量");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
