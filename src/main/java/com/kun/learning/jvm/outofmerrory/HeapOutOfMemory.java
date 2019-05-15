package com.kun.learning.jvm.outofmerrory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrjiakun on 2019/1/9
 *
 * 内存溢出，监控工具Jprofile
 *  以 -X 开头的是非标准选项， 不是所有的虚拟机都支持           , -XX 的选项是不稳定、不建议随便使用的。但是所有的虚拟机都支持，对虚拟机的运行性能影响较大
 * 设置参数：
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * -Xms 初始化堆内存大小
 * -Xmx 最大堆内存大小
 *
 * 其中HeapDumpOnOutOfMemoryError让虚拟机在内存溢出时候Dump出当前内存堆转存储快照以便事后分析
 *
 * -XX:+HeapDumpOnOutOfMemoryError
    该配置会把快照保存在用户目录或者tomcat目录下，也可以通过 -XX:HeapDumpPath=/tmp/heapdump.hprof 来显示指定路径
 *
 */
public class HeapOutOfMemory {

    List<Content> contents  = new ArrayList<>();
    public static void main(String[]args) throws Exception{
        HeapOutOfMemory heapOutOfMemory = new HeapOutOfMemory();
       while(true){
            heapOutOfMemory.contents.add(new Content());
            Thread.sleep(2);
        }
    }
}
class Content{
    Byte [] content = new Byte[2];
}