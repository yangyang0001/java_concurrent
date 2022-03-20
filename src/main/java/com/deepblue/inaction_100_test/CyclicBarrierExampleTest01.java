package com.deepblue.inaction_100_test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 */
public class CyclicBarrierExampleTest01 {

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("the last processor invoke!");
        }
    });

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            System.out.println("threadA invoke ...");
            try {
                Thread.sleep(10000L);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            System.out.println("threadB invoke ...");
            try {
                Thread.sleep(10000L);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();

    }






}
