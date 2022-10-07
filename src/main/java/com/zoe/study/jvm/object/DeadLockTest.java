package com.zoe.study.jvm.object;

public class DeadLockTest {
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj1) {
                System.out.println("Thread1 拿到了obj1的锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj2) {
                    System.out.println("Thread1 拿到了obj2的锁");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (obj2) {
                System.out.println("Thread1 拿到了obj2的锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj1) {
                    System.out.println("Thread1 拿到了obj1的锁");
                }
            }
        }).start();
    }
}
