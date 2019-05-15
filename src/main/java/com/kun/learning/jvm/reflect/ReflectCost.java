package com.kun.learning.jvm.reflect;

import java.lang.reflect.Method;

/**
 * Created by jrjiakun on 2019/1/7
 *
 *
 * 通过反编译，找到关键的代码：
 *
 *59: aload_2                         // 加载 Method 对象
 *60: aconst_null                     // 反射调用的第一个参数 null
 *61: iconst_1
 * 62: anewarray Object                // 生成一个长度为 1 的 Object 数组
 *65: dup
 * 66: iconst_0
 *67: sipush 128
 *70: invokestatic Integer.valueOf    // 将 128 自动装箱成 Integer
 *73: aastore                         // 存入 Object 数组中
 *74: invokevirtual Method.invoke     // 反射调用
 *
 *
 * 因此此时有2个关键的操作：
 * 1. 新建一个Object数组，将传入的数组粗放在该数组中
 *
 * 2. Object数组不能存放基本类型，此时需要自动装箱 （java缓存了-128  to  127的Integer对象，可以通过-Djava.lang.Integer.IntegerCache.high=128来调整缓存范围）
 *
 * 因此，带来的问题有可能使得GC频繁（可以通过-XX:+PrintGC参数查看）
 *  结果如下：
 * [GC (Allocation Failure)  5150K->670K(15936K), 0.0001993 secs]
 * [GC (Allocation Failure)  5150K->670K(15936K), 0.0001929 secs]
 *
 * Allocation Failure： 表明年轻代中没有足够的空间能够存储新的数据了
 *5150K->670K(15936K)  ： GC前该内存区域(这里是年轻代)使用容量，GC后该内存区域使用容量，该内存区域总容量。
 *
 *因此可以通过优化：
 *
 * 1. 将Integer放在循环外
 *
 * 2. 在循环外构建参数数组
 *
 *
 *  但是，发现，其实性能还不如之前的版本好，为啥呢？因为在循环外建立数组，编译器无法确定这个数组会不会中途被更改，无法优化访问数组的操作
 *
 *  进一步优化：
 *  关闭本地调用，直接用动态调用
 *  关闭权限检查
 *  method.setAccessible(true)
 *
 *   反射调用变快，还有个原因是方法被内联。
 *
 * 但是实际上，如果反射调用的方法很多，可能造成有的反射调用没有被内联，如RelectCost_Inline
 *
 *
 * 总结起来，反射性能的开销主要有：
 * 1. 边长方法导致的Object数组
 * 2. 基本类型的自动装箱和拆箱
 * 3. 方法的内联
 *
 *
 */
public class ReflectCost {
    public static void target(int i) {
        // 空方法
    }

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("com.kun.learning.jvm.reflect.ReflectCost");
        Method method = klass.getMethod("target", int.class);

        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            method.invoke(null, 128);
        }
    }
}
