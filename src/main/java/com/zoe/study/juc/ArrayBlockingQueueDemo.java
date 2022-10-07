package com.zoe.study.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        Interviewer interviewer = new Interviewer(blockingQueue);
        Engineers engineers = new Engineers(blockingQueue);
        new Thread(interviewer).start();
        new Thread(engineers).start();

    }

    static class Interviewer implements Runnable {
        private BlockingQueue<String> queue;

        public Interviewer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("面试官，我准备好面试了");
            String msg;
            try {
                while (!(msg = queue.take()).equals("stop")) {
                    System.out.println(msg + "面试开始了");
                    Thread.sleep(1000);
                    System.out.println(msg + "面试结束了");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("所有人面试结束");
        }
    }

    static class Engineers implements Runnable {
        private BlockingQueue<String> queue;

        public Engineers(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String msg = "程序员" + i;
                try {
                    queue.put(msg);
                    System.out.println(msg + "就坐，等待面试");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                queue.put("stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
