package com.zoe.study.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.7:-XX:PermSize=8m -XX:MaxPermSize=8m -Xmx16m
 * 1.8:-XX:PermSize=8m -XX:MaxPermSize=8m -Xmx16m
 * -Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
 */
public class StringOomMock {
    static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
