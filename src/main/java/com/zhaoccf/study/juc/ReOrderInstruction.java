package com.zhaoccf.study.juc;

public class ReOrderInstruction {
    private static int num = 0;
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        WriteThread writeThread = new WriteThread();

        Thread.sleep(5);
        readThread.start();
        writeThread.start();
        Thread.sleep(5);
        readThread.interrupt();
        System.out.println("main done");
    }

    static class ReadThread extends Thread{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println("read:" +(num + num));
                }
                System.out.println("read is done");
            }
        }
    }

    static class WriteThread extends Thread{
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write is done");
        }
    }
}
