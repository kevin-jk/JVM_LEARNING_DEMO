package com.kun.learning.jvm.outofmerrory;

/**
 * @Author: jrjiakun
 * @Date: 2019/1/31 21:23
 *
 * 在HotSpot中，实际上不区分虚拟机栈和本地方法栈
 *
 * 对于栈容量可以由-Xss 参数设定
 *
 * 对于java虚拟机栈的异常有2种：
 *
 * 1. 线程请求的栈深度大于所允许的最大深度，将抛出stackOverflowError
 *
 * 2. 如果是在扩展时候无法申请到足够的内存，将抛出 OutOfMerryError
 *
 *
 * -Xss128k
 *
 * 如果调整栈容量，其抛出的异常仍然是stackOverflowError
 *
 * 那么如何让其抛出OutOfMerry呢？在多线程下，可以通过创建线程来达到
 */
public class StackOOM {
    public int depth = 1;

    private void StatckOverFlowLoop(){
        depth++;
        StatckOverFlowLoop();
    }

    private void OOMLoop(){
        while(true){
            new Thread(()->{
                while(true){

                }
            }).start();
        }
    }
    //java.lang.StackOverflowError
    public static void main(String[]args){
        StackOOM stackOOM = new StackOOM();
        boolean flag = "1".equals(args[0]);

        try{
            if(!flag){
                stackOOM.StatckOverFlowLoop();
            }else {
                // 多线程环境下，OOM 可以通过减少线程分配内存来解决
                // 通常情况下，OOM通过扩大内存，这一点恰好与之相反
                stackOOM.OOMLoop();
            }

        }catch (Throwable e){
            System.out.println(stackOOM.depth);
        }

    }
}
