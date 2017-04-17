package com.pdy.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.pdy.util.TimeTrace;

public class CopyOnArrayListTest implements Runnable {

    private List<Integer> list;



    public CopyOnArrayListTest(List<Integer> list) {
        super();
        this.list = list;
    }

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        synchronized (obj) {
        obj.wait();
        }
        // 测试ArrayList会不会出现异常
        List<Integer> list = new CopyOnWriteArrayList<Integer>();// new
                                                                 // ArrayList<Integer>();
        ExecutorService es = Executors.newFixedThreadPool(1);
        TimeTrace.trace();
        es.execute(new CopyOnArrayListTest(list));
        // es.execute(new CopyOnArrayListTest(list));
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        TimeTrace.trace();
        TimeTrace.print();


    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
    }

}
