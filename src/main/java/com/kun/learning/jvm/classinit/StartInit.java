package com.kun.learning.jvm.classinit;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/2 14:59
 *
 *  * java虚拟机严格规定了只有5种情况会初始化类
 *  * 1. new, getstatic, putstatic, invokestatic 指令的时候，才会触发初始化。对应于new, 读取或者设置一个静态字段， 以及调用一个静态方法的时候
 *  *  2. reflect
 *  *  3. 初始化其父类
 *  *  4. main函数所在的类
 *  *  5、 jdk 7 zhong MethodHandle 动态语言所解析的实例
 *
 *
 */
public class StartInit {
    public static void main(String[]args){
      //  readParentValue();
       // buildArray();
        readConstant();
    }

    /**
     * 虽然是用的Child的类引用的value,但是value属于Parent， 因此此时并不会去初始化Child
     * */
    private static void readParentValue(){
        System.out.println(Child.value);
    }
    /**
     * 通过数组定义来引用类，不会触发类的初始化
     * */
   private static void buildArray(){
        Child[] children = new Child[10];
   }

   /**
    * 常量在编译阶段会存入调用类的常量池中
    *
    * 本质上并没有引用到定义常量的类
    * */
   private static void readConstant(){
       System.out.println(Parent.HELLO_WORLD);
   }
}
