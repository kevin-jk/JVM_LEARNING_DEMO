package com.kun.learning.jvm.handleMethod;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * Created by jrjiakun on 2019/1/8
 *
 *方法句柄是一个强类型的，能够被直接执行的引用
 *
 * 该引用可以指向常规的静态方法，或者实例方法，还可以之下那个构造器和字段
 *
 *
 *
 *
 * 描述：方法句柄是Java7为间接调用方法而引入的新API, 同Java的反射API和代理有类似功能，但是简化了使用。
 * Reflection和MethodHandle区别(引自yushuifirst《MethodHandle与反射Method区别，invokedynamic指令》):
 * 1、Reflection和MethodHandle机制本质上都是在模拟方法调用，但是Reflection是在模拟Java代码层次的方法调用，而MethodHandle是在模拟字节码层次的方法调用。
 * 2、Reflection中的Method对象远比MethodHandle机制中的MethodHandle对象所包含的信息要多。前者是方法在Java一端的全面映像，包含了方法的签名、描述符以及方法属性表中各种属性的Java端表示方式，
 * 还包含有执行权限等的运行期信息。而后者仅仅包含着与执行该方法相关的信息。通俗的话说，Reflection是重量级，而MethodHandle是轻量级。
 * 3、由于MethodHandle是对字节码的方法指令调用的模拟，那理论上虚拟机在这方面做的各种优化（如方法内联），在MethodHandle上也应当可以采用类似思路去支持（但目前实现还不完善）。而通过反射去调用方法则不行。
 * 4、Reflection API的设计目标是只为Java语言服务的，而MethodHandle则设计为可服务于所有Java虚拟机之上的语言，其中也包括了Java语言而已。
 *
 *
 */
public class HandleMethodDemo {
    private static void bar(Object o) {
        System.out.println("bar");
    }
    /**
     * java.lang.invoke.MethodHandle 类似于反射中的Method，是对要执行的方法的一个引用，我们也是通过它来调用底层方法，
     * 它调用时有两个方法 invoke和invokeExact，后者要求参数类型与底层方法的参数完全匹配，前者则在有出入时做修改如包装类型。
     * java.lang.invoke.MethodType 方法签名不可变对象，是对方法的一个映射，包含返回值和参数类型（不关心类名和方法名）。在lookup时也是通过它来寻找的。
     * java.lang.invoke.MethodHandles.lookup().findVirtual
     */

   public static MethodHandles.Lookup getLookUp(){
       return MethodHandles.lookup();
   }

}
