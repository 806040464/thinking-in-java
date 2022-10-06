package com.zhaoccf.study.jvm.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class DriverLoad {
    public static void main(String[] args) {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        if (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:"+driver.getClass()+"classloader:"+driver.getClass().getClassLoader());
        }
        System.out.println("thread context loader:"+Thread.currentThread().getContextClassLoader());
        System.out.println("serviceloader:"+ServiceLoader.class.getClassLoader());
    }
}
