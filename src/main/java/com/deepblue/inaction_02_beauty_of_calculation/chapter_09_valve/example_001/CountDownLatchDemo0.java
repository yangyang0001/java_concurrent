package com.deepblue.inaction_02_beauty_of_calculation.chapter_09_valve.example_001;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo0 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(1);

        Thread threadA = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                cdl.await();
                System.out.println("threadA invoke running");
            }

        });

        Thread threadB = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                cdl.await();
                System.out.println("threadB invoke running");
            }

        });

        threadA.start();
        threadB.start();

        Thread.currentThread().sleep(2000L);
        cdl.countDown();

        System.out.println("main thread invoke end");
    }
}
