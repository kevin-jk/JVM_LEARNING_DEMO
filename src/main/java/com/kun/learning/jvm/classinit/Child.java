package com.kun.learning.jvm.classinit;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/2 14:59
 */
public class Child extends Parent {
    static {
        System.out.println("Child init....");
    }
}
