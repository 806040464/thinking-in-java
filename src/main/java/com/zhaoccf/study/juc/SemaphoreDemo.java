package com.zhaoccf.study.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        //抢车位模拟程序
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位了");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "停3s离开了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, "Car"+i).start();
        }
    }
}
