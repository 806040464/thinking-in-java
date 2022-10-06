package com.zhaoccf.study.jvm.classloader;

import java.io.*;

public class CustomClassLoader extends ClassLoader {

    private String classpath;

    public CustomClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String path = classpath + File.separator + name.replace(".", File.separator) + ".class";

        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            byte[] classData = bos.toByteArray();
            return defineClass(name, classData, 0, classData.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = classpath + File.separator + name.replace(".", File.separator) + ".class";

        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            byte[] classData = bos.toByteArray();
            return defineClass(name, classData, 0, classData.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }
}
