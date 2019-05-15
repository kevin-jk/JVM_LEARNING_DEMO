package com.kun.learning.jvm.exc;

/**
 * Created by jrjiakun on 2019/1/3
 *
 * 反编译后javap -v ExceptionDemo得到字节码
 *
 *
 */
public class ExceptionDemo {
    public static void main(String[]args){
        try{
            mayBeThrowException(true);
        }catch (Exception e){
            System.out.println("Cache exception, e:"+e);
        }finally {
            System.out.println("finally");
        }
    }

    private static void mayBeThrowException(boolean flag) throws Exception{
       if(flag){
           throw new Exception("mayBeThrowException");
       }
    }
}
