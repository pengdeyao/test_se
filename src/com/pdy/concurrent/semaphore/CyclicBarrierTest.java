package com.pdy.concurrent.semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        // cyclic 设置一个公共点，让所有线程集合，然后再继续执行。 到达集合点的时候还可以执行定义的别的操作

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人集合完毕休息10s");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cyclicBarrier = new CyclicBarrier(3);
        Thread t1 = new Thread(new Worker4CB(cyclicBarrier, "A"));
        Thread t2 = new Thread(new Worker4CB(cyclicBarrier, "B"));
        Thread t3 = new Thread(new Worker4CB(cyclicBarrier, "C"));
        t1.start();
        t2.start();
        t3.start();
    }

}

class Worker4CB implements Runnable {

    private CyclicBarrier cyclickBarrier = null; // 栅栏
    private String workerName = ""; // 工人名称

    public Worker4CB(CyclicBarrier cyclicBarrier, String workerName) {
        super();
        this.cyclickBarrier = cyclicBarrier;
        this.workerName = workerName;
    }



    @Override
    public void run() {
        System.out.println(workerName + "开始执行");
        try {
            System.out.println(workerName + "到达目的地，等待其他人一起集合");
            cyclickBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(workerName + "集合休息完毕，继续出发");

    }

}
