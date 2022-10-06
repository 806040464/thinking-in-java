package com.zhaoccf.study.jvm.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * -Xms60m -Xmx60m -XX:+PrintGCDetails
 */
public class HeapInstance {
    public static void main(String[] args) {
        List<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Picture picture = new Picture(new Random().nextInt(1024 * 1024));
            list.add(picture);
        }
    }


    public static class Picture {
        private byte[] bytes;

        public Picture(int length) {
            this.bytes = new byte[length];
        }
    }
}
