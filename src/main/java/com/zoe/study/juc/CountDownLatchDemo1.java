package com.zoe.study.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo1 implements Runnable {
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        //模拟火箭发射检查程序
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatchDemo1 countDownLatchDemo1 = new CountDownLatchDemo1();
        for (int i = 0; i < 10; i++) {
            executor.submit(countDownLatchDemo1);
        }
        countDownLatch.await();
        System.out.println("fire");
        executor.shutdown();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "check complete");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            countDownLatch.countDown();
        }
    }
}
