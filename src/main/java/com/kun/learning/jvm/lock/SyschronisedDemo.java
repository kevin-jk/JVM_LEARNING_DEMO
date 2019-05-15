package com.kun.learning.jvm.lock;

/**
 * @Author: jrjiakun
 * @Date: 2019/1/23 15:15
 *
 * // Run with -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatistics -XX:TieredStopAtLevel=1   -XX:BiasedLockingStartupDelay=0
 */
public class SyschronisedDemo {
    static Lock lock = new Lock();
    static int counter = 0;

    public static void foo() {
        synchronized (lock) {
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
  //   lock.hashCode(); // Step 2
      //  System.identityHashCode(lock); // Step 4
        for (int i = 0; i < 1_000_000; i++) {
            foo();
        }
    }

    static class Lock {
       @Override public int hashCode() { return 0; } // Step 3
    }
}
