package com.zoe.study.juc;

public class SynchronizedDemo {
    public static void main(String[] args) {

    }

    public void sync1(){
        synchronized (this) {
            int a = 1;
        }
    }

    public synchronized void sync2() {
        int a = 1;
    }

    public static synchronized void sync3() {
        int a = 1;
    }
}
