package com.kun.learning.jvm.reflect;

import java.lang.reflect.Method;

/**
 * Created by jrjiakun on 2019/1/7
 *
 *
 *-XX:TypeProfileWidth 调整虚拟机记录类型的数目
 *
 *
 */
public class ReflectCost_Inline {
    public static void target(int i) {
        // 空方法
    }
    public static void target1(int i) { }
    public static void target2(int i) { }

    public static void polluteProfile() throws Exception {
        Method method1 = ReflectCost_Inline.class.getMethod("target", int.class);
        Method method2 = ReflectCost_Inline.class.getMethod("target", int.class);
        for (int i = 0; i < 2000; i++) {
            method1.invoke(null, 0);
            method2.invoke(null, 0);
        }
        System.out.println(method1.equals(method2));
    }


    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("com.kun.learning.jvm.reflect.ReflectCost_Inline");
        Method method = klass.getMethod("target", int.class);
        method.setAccessible(true);  // 关闭权限检查
        polluteProfile();

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
