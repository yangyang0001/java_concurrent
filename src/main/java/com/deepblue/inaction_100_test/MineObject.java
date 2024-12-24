package com.deepblue.inaction_100_test;

/**
 *
 */
public class MineObject {

    private static int count = 0;

    public static synchronized void count() throws InterruptedException {
        count ++;
        System.out.println("current thread id = " + Thread.currentThread().getId() + ", count method invoke, count = " + count);
        Thread.currentThread().sleep(1000L);

        if(count < 4) {
            count();
        }
    }

}
