package com.kun.learning.jvm.classinit;

/**
 * Created by jrjiakun on 2018/12/14
 *
 *
 *
 * $ java -cp /path/to/asmtools.jar org.openjdk.asmtools.jdis.Main Singleton\$LazyHolder.class > Singleton\$LazyHolder.jasm.1
 * $ awk 'NR==1,/stack 1/{sub(/stack 1/, "stack 0")} 1' Singleton\$LazyHolder.jasm.1 > Singleton\$LazyHolder.jasm
 * $ java -cp /path/to/asmtools.jar org.openjdk.asmtools.jasm.Main Singleton\$LazyHolder.jasm
 * $   java -verbose:class Singleton



 *
 *
 */
public class ClassInitDemo {
    private ClassInitDemo() {}
    private static class LazyHolder {
        static final ClassInitDemo INSTANCE = new ClassInitDemo();
        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }
    public static Object getInstance(boolean flag) {
        if (flag) return new LazyHolder[2];
        return LazyHolder.INSTANCE;
    }
    public static void main(String[] args) {
        getInstance(true);
        System.out.println("----");
        getInstance(false);
    }
}



