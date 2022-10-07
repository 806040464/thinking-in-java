package com.zoe.study.jvm.object;

import org.openjdk.jol.info.ClassLayout;

public class ObjSize {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        int[] ints = new int[10];
        System.out.println(ClassLayout.parseInstance(ints).toPrintable());
        Object[] objs = new Object[10];
        System.out.println(ClassLayout.parseInstance(objs).toPrintable());
    }
}
