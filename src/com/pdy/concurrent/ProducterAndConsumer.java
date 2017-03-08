package com.pdy.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产着消费者模型
 * 
 * @author pengdeyao
 *
 */
public class ProducterAndConsumer {

    public static void main(String[] args) throws InterruptedException {
        int maxCapacity = 10000;
        List<Integer> respository = new ArrayList<Integer>();
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(new Producter(respository, maxCapacity));
        es.execute(new Producter(respository, maxCapacity));
        es.execute(new Consumer(respository));
        es.execute(new Consumer(respository));
        es.shutdown();
        es.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);

    }

}

class Producter implements Runnable {
    /**
     * 仓库
     */
    private List<Integer> respository;

    /**
     * 仓库最大容量
     */
    private int maxCapcity;

    public Producter(List<Integer> respository, int maxCapcity) {
        super();
        this.respository = respository;
        this.maxCapcity = maxCapcity;
    }



    @Override
    public void run() {
        // 往仓库里面生产产品,如果仓满停止生产，等待消费者消费后通知可以生产
        while (true) {
            try {
                // 锁定仓库避免并发生产或消费
                synchronized (respository) {
                    if (respository.size() >= maxCapcity) {
                        respository.notifyAll();
                        System.out.println(Thread.currentThread().getName() + "生产者等待");
                        respository.wait();//
                    }
                    respository.add(1);
                    System.out.println(Thread.currentThread().getName() + "放入仓库capacity=" + respository.size());
                    respository.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}

class Consumer implements Runnable {
    private List<Integer> respository;

    public Consumer(List<Integer> respository) {
        super();
        this.respository = respository;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (respository) {

                    if (respository.isEmpty()) {
                        respository.notifyAll();
                        System.out.println(Thread.currentThread().getName() + "消费者等待");
                        respository.wait();
                        System.out.println(Thread.currentThread().getName() + "消费者等待后被唤醒");
                    } else {
                        Integer product = respository.get(0);
                        respository.remove(0);
                        System.out.println(Thread.currentThread().getName() + "消费商品" + product);
                        respository.notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

}
