package com.kun.learning.jvm.MemoryModel;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;
/**
 * @Author: jrjiakun
 * @Date: 2019/1/23 14:35
 *
 * jcstress?
 */

@JCStressTest
@Outcome(id = {"0, 0", "0, 2", "1, 0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"1, 2"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Abnormal outcome")
@State
public class ConcurrencyDemo {
    int a=0;
    int b=0; // 改成 volatile 试试？
    @Actor
    public void method1(II_Result r) {

    }
    @Actor
    public void method2(II_Result r) {

    }
}
