package com.kun.learning.jvm.handleMethod;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * Created by jrjiakun on 2019/1/8
 */
public class Main {
    public static void main(String[] args) throws Throwable {
        // 方法句柄权限的检查在句柄创建的时候完成，在实际的调用过程中不会检查方法句柄的权限
        // 方法句柄的访问权限不取决于方法句柄的创建位置，而是取决于Lookup对象的创建位置
        // MethodHandles.Lookup l = HandleMethodDemo.getLookUp(); // 具备HandleMethod类的访问权限


        MethodHandles.Lookup l = MethodHandles.lookup();  //exception, 没有访问权限
        Method m = HandleMethodDemo.class.getDeclaredMethod("bar", Object.class);
        MethodHandle mh0 = l.unreflect(m);

        mh0.invoke(new Object());
        MethodType t = MethodType.methodType(void.class, Object.class);
        //l.find***
        MethodHandle mh1 = l.findStatic(HandleMethodDemo.class, "bar", t);
        // 调用方法句柄和原本的调用指令是一致的，即原有的invokevirtual调用方法句柄，她也会采用动态绑定

        //自动适配参数类型，invoke会调用MethodHandle.asType方法来生成一个适配器方法句柄，对传入的方法进行适配
        mh1.invoke(new Object());
        mh1.invokeWithArguments(new Object());
        // 严格按照参数类型进行匹配
        // 方法中有个PolymorphicSignature注解，在被其注解的方法时候，编译器会根据所传入的参数声明类型来生成方法描述符，而不是采用目标方法所声明的描述符
        mh1.invokeExact(new Object());

        //除此之外，方法句柄还支持增删改参数的操作。（改操作，如上面invoke方法提到的MethodHandle.asType方法）
        // 删除操作
       // MethodHandles.dropArguments()
        //插入操作
        //MethodHandles.insertArguments()
        // 一个执行f(x,y)的方法句柄，可以绑定x=4,生成一个g(4,y)的句柄。当调用g的时候，会在参数列表前面插入一个4，然后再调用f

    }
}
