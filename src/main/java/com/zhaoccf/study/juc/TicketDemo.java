package com.zhaoccf.study.juc;

import org.springframework.util.StopWatch;

public class TicketDemo {

    public static void main(String[] args) throws InterruptedException {
        // 三个售票员线程共享100张票，模拟卖票
        SellTicketTask sellTicketTask = new SellTicketTask();
        Thread thread1 = new Thread(sellTicketTask);
        Thread thread2 = new Thread(sellTicketTask);
        Thread thread3 = new Thread(sellTicketTask);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }


    static class SellTicketTask implements Runnable {
        private volatile int ticketNum = 50;

        @Override
        public void run() {
            while (ticketNum > 0) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "正在卖：" + ticketNum--);
            }
        }
    }
}
