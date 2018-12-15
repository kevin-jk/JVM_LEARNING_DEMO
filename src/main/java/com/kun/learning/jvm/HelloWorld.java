package com.kun.learning.jvm;

/**
 * Created by jrjiakun on 2018/12/12
 *
 * NMT 的使用
 *
 * -XX:NativeMemoryTracking=summary -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics -XX:+UseSerialGC
 *
 * 查看各个区域占用空间
 */
public class HelloWorld {
    public static void main(String[]args){
        System.out.println("Hello world");
    }
}
