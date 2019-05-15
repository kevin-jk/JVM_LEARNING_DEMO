package com.kun.learning.jvm.gc;

/**
 * Created by jrjiakun on 2019/1/10
 */


// time java SafepointTestp
//你还可以使用如下几个选项
// -XX:+PrintGC  (-XX:+PrintGCDetails 打印垃圾回收日志，在退出时候输出当前各个区域的内存分配情况)
// -XX:+PrintGCApplicationStoppedTime
// -XX:+PrintSafepointStatistics
// -XX:+UseCountedLoopSafepoints
public class FullGcLongStop {
    static double sum = 0;

    public static void foo() {
        for (int i = 0; i < 0x77777777; i++) {
            sum += Math.sqrt(i);
        }
    }

    public static void bar() {
        for (int i = 0; i < 50_000_000; i++) {
            new Object().hashCode();
        }
    }

    public static void main(String[] args) {
        new Thread(FullGcLongStop::foo).start();
       new Thread(FullGcLongStop::bar).start();
    }
}
