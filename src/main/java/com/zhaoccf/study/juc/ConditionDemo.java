package com.zhaoccf.study.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(() -> new HashMap<>());

    public static void main(String[] args) {
        // 模拟线程间通信，按洗剪吹顺序执行5次
        HairSalon hairSalon = new HairSalon();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                hairSalon.wash();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                hairSalon.cut();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                hairSalon.blow();
            }
        }).start();
    }

    static class HairSalon {
        private volatile int status = 1;
        private ReentrantLock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        private void wash() {
            lock.lock();
            try {
                while (1 != status) {
                    c1.await();
                }
                System.out.println(Thread.currentThread().getName() + "洗头");
                status = 2;
                c2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        private void cut() {
            lock.lock();
            try {
                while (2 != status) {
                    c2.await();
                }
                System.out.println(Thread.currentThread().getName() + "剪头");
                status = 3;
                c3.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        private void blow() {
            lock.lock();
            try {
                while (3 != status) {
                    c3.await();
                }
                System.out.println(Thread.currentThread().getName() + "吹头");
                status = 1;
                c1.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
