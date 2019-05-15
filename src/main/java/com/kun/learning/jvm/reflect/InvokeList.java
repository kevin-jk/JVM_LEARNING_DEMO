package com.kun.learning.jvm.reflect;

import java.lang.reflect.Method;

/**
 * Created by jrjiakun on 2019/1/7
 *
 * 调用过程：
 *
 * 1. DelegatingMethodAccessorImpl         委派实现
 * 2. NativeMethodAccessorImpl    本地实现
 *
 * 实际上，java的反射机制还设立了一种动态生成字节码的实现，直接使用invoke指令来调用目标方法
 *
 * 使用委派实现，就是为了能够在本地实现和动态实现中切换
 *
 * 动态实现比本地实现快（动态实现无需经过java到c++再到java的切换，但是由于生成字节码较慢，仅仅一次调用的话，本地实现要快一些）
 *
 * jvm设置一个阈值，15， 在15次调用内，采用本地调用，在15次调用外，采用动态实现，这个过程叫做Inflation, 可以通过（-Dsun.refletct.noInflation=true）命令来关闭
 * 这样的话，就会一开始就动态实现，而不适用委派实现和本地实现
 *
 *反射以getMethod为代表的查找方法操作，会返回查找得到结果的一份拷贝。（因此对通过一对象的getMethod， 2次调用== 时候为false, equals时候为 true）
 *
 *@link https://rednaxelafx.iteye.com/blog/548536
 *
 */
public class InvokeList {
    static int count = 0;
    public static void target(int i) {

        if(count<14){

        }else{
            new Exception("#" + i).printStackTrace();
        }
      count++;
    }

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("com.kun.learning.jvm.reflect.InvokeList");
        Method method = klass.getMethod("target", int.class);
        for(int i=0;i<20;i++)
        method.invoke(null, i);
    }
}
