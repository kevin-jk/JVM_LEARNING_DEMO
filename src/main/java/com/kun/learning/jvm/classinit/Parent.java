package com.kun.learning.jvm.classinit;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/2 14:58
 */
public class Parent {

    public static  int value = 10;

    public static final String HELLO_WORLD="hello world";
    static{
        System.out.println("Parent init....");
    }
}
