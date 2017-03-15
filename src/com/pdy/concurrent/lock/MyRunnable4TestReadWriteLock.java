package com.pdy.concurrent.lock;

public class MyRunnable4TestReadWriteLock implements Runnable {
    /** 是否是写线程 */
    private boolean isWriter = false;
    private Counter counter = null;


    public MyRunnable4TestReadWriteLock(boolean isWriter, Counter counter) {
        super();
        this.isWriter = isWriter;
        this.counter = counter;
    }



    @Override
    public void run() {
        while (true) {
            try {
                if (isWriter) {
                    counter.incr();
                } else {
                    counter.getNum();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
