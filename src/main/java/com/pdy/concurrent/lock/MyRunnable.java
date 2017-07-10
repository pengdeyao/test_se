package com.pdy.concurrent.lock;

public class MyRunnable implements Runnable {

    private Counter counter = null;

    public MyRunnable(Counter counter) {
        super();
        this.counter = counter;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100000) {
            i++;
            try {
                counter.incr();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
