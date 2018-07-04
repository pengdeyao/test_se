package com.pdy.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/** 阅读 AbstractQueueSynchronizer 代码 ，自定义一个只允许两个线程同时执行的锁。
 * Created by pengdeyao on 2018/7/3
 */
public class TwinsLock {

    private Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer{

        public Sync(int count){
            super.setState(count);
        }

        public int tryAcquireShared(int reduceCount){
            int current = getState();
            int newCount = current - reduceCount;
            if(newCount < 0 ){
                return newCount;
            }
            if( compareAndSetState(current , newCount)){
                System.out.println(Thread.currentThread().getName() +"  get lock currState="+newCount);

            }
            return newCount;

        }

        public boolean tryReleaseShared(int returnCount){
            int current = getState();
            int newCount = current + returnCount;
            if(newCount > 2){
                return false;
            }
            if(  compareAndSetState(current,newCount) ){
                System.out.println(Thread.currentThread().getName() +"  release lock currState="+newCount);
                return true;
            }
            return false;
        }
    }

    public void lock(){
        sync.acquireShared(1);
    }

    public void unlock(){
        sync.releaseShared(1);
    }


    public static void main(String[] args) throws InterruptedException {
        final TwinsLock twinsLock = new TwinsLock();
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    twinsLock.lock();
//                    System.out.println(Thread.currentThread().getName() +" "+System.currentTimeMillis());
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    twinsLock.unlock();
                }
            });

            if(i % 2 == 0){
                Thread.sleep(100);
            }
        }
        es.shutdown();
        es.awaitTermination(0,TimeUnit.MINUTES);

    }

}
