package com.zhaoccf.study.jvm.classloader;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

//@RunWith(SpringRunner.class)
//@SpringBootTest
class CustomClassLoaderTest {

    @Test
    void findClass() throws Exception {
        CustomClassLoader customClassLoader = new CustomClassLoader("C:\\Users\\80604\\Desktop");
        Class<?> c = customClassLoader.loadClass("com.zhaoccf.study.jvm.classloader.Test");
        if (c!=null){
            Object obj = c.newInstance();
            Method method = c.getMethod("say");
            method.invoke(obj,null);
            System.out.println(obj.getClass().getClassLoader());
            System.out.println(c.getClassLoader());
        }
    }

    @Test
    void loadClass() throws Exception {
        CustomClassLoader customClassLoader = new CustomClassLoader("C:\\Users\\80604\\Desktop");
        Class<?> c = customClassLoader.loadClass("java.lang.String");
        if (c!=null){
            Object obj = c.newInstance();
            Method method = c.getMethod("say");
            method.invoke(obj,null);
            System.out.println(obj.getClass().getClassLoader());
            System.out.println(c.getClassLoader());
        }
    }
}