package com.kun.learning.jvm.exc;

/**
 * Created by jrjiakun on 2019/1/3
 */
public class ExceptionDemo2 {
    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;

    public void test() {
        try {
            tryBlock = 0;
        } catch (Exception e) {
            catchBlock = 1;
        } finally {
            finallyBlock = 2;
        }
        methodExit = 3;
    }
}
