package com.kun.learning.jvm.objectgcsaveself;

/**
 * Created by jrjiakun on 2019/1/10
 */
public class ObjectSaveSelf {
    private static ObjectSaveSelf HOOK = null;

    public void finalize(){
        System.out.println("Finalize");
        HOOK = this;
    }
    public static void main(String[]args) throws Exception{
        HOOK  = new ObjectSaveSelf();
        HOOK = null;
        System.gc();
        // gc优先级低，等待gc
        Thread.sleep(5000);
        if(HOOK!=null){
            System.out.println("Alive");
        }else {
            System.out.println("Die");
        }

       // 第二次gc, 由于finalize已经被执行过，所以不会拯救自己
        HOOK = null;
        System.gc();
        // gc优先级低，等待gc
        Thread.sleep(5000);
        if(HOOK!=null){
            System.out.println("Alive");
        }else {
            System.out.println("Die");
        }
    }
}
