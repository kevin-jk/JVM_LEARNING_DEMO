package com.kun.learning.jvm;

/**
 * Created by jrjiakun on 2018/12/12
 *
 * NMT 的使用
 *
 * -XX:NativeMemoryTracking=summary -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics -XX:+UseSerialGC
 *
 * 查看各个区域占用空间
 *
 *
 *
 *
 * 修改字节码
 * 1. java -cp "D:\Program Files\Java\jdk1.8.0_45\tools\asmtools.jar" org.openjdk.asmtools.jdis.Main HelloWorld.class > HelloWorld.jasm.1
 * 2. awk 'NR==1,/iconst_1/{sub(/iconst_1/, "iconst_2")} 1' HelloWorld.jasm.1 > HelloWorld.jasm
 * 3. java -cp "D:\Program Files\Java\jdk1.8.0_45\tools\asmtools.jar" org.openjdk.asmtools.jasm.Main HelloWorld.jasm
 * 4. java HelloWorld
 *
 *
 */
public class HelloWorld {
    public static void main(String[]args){
        boolean flag = true;
        if (flag)
            System.out.println("Hello, world");
        if(flag==true){
            System.out.println("Hello, JVM");
        }

    }
}
