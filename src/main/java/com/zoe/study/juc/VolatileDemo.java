package com.zoe.study.juc;

public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        Task1 task1 = new Task1();
        new Thread(task1).start();
        Task2 task2 = new Task2();
        new Thread(task2).start();

        Thread.sleep(1000);
        task1.flag = false;
        System.out.println("Task1修改为false");
        task2.flag = false;
        System.out.println("Task2修改为false");


        Task3 task3 = new Task3();
        new Thread(task3).start();
        new Thread(task3).start();
        Task4 task4 = new Task4();
        new Thread(task4).start();
        new Thread(task4).start();

        Thread.sleep(1000);
        System.out.println(task3.num);
        System.out.println(task4.num);
    }

    static class Task1 implements Runnable{
        public boolean flag = true;

        @Override
        public void run() {
            System.out.println("Task1开始");
            while (flag) {
            }
            System.out.println("Task1结束");
        }
    }

    static class Task2 implements Runnable{
        public volatile boolean flag = true;

        @Override
        public void run() {
            System.out.println("Task2开始");
            while (flag) {
            }
            System.out.println("Task2结束");
        }
    }

    static class Task3 implements Runnable{
        public int num;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                num++;
            }
        }
    }

    static class Task4 implements Runnable{
        public volatile int num;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                num++;
            }
        }
    }
}
