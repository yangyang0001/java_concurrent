package com.deepblue.inaction_100_test;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class CountDownLatchExampleTest01 {

    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            try {
                System.out.println("threadA invoke before ...");
                countDownLatch.await();
                System.out.println("threadA is running ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                System.out.println("threadB invoke before ...");
                countDownLatch.await();
                System.out.println("threadB is running ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();

        Thread.sleep(1000L);

        countDownLatch.countDown();

    }
}
