package com.kun.learning.jvm.gc;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/1 15:06
 *
 * ===============================
 *   JVM args:
 *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * ===============================
 *
 *Java -verbose:gc 中参数-verbose:gc 表示输出虚拟机中GC的详细情况.
 * SurvivorRatio = 8  表示 Eden 区域与一个Survivor区域的比例为8:1， 在这里就是Eden 区域就是 10M * 8 / (8+1+1) = 8M
 *

 */
public class AllocationGC {

    private static final int _1MB = 1024*1024;

    public static void main(String[]r){
        byte [] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }
}
