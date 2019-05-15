package com.kun.learning.jvm.outofmerrory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/1 10:15
 *
 * 方法区的OOM
 *
 * 我们知道方法区还包括常量池，那么怎么让这个区域内存溢出？
 *
 * 1. 让常量池溢出  java String 在常量池中
 * 2. 让不断的加载类
 *
 *
 * 可以通过-XX:PermSize 和 -XX:MaxPermSize 来限制方法区的大小
 *
 * String.intern(）
 *
 * -XX:PermSize=1M -XX:MaxPermSize=1M      (JDK 8 中该参数已经没用)
 *
 * jdk 6 才能测试
 *
 *
 */
public class PergenOOM {
    public static void main(String[]args){
        List<String> ss = new ArrayList<String>();
        int i = 0;
        while (true){
            ss.add(String.valueOf(i).intern());
        }
    }
}
