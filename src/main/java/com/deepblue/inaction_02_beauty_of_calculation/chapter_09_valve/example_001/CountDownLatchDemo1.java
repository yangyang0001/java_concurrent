package com.deepblue.inaction_02_beauty_of_calculation.chapter_09_valve.example_001;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(2);

        Thread threadA = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000L);
                System.out.println("threadA invoke running");
                cdl.countDown();
            }

        });

        Thread threadB = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000L);
                System.out.println("threadA invoke running");
                cdl.countDown();
            }

        });

        threadA.start();
        threadB.start();
        cdl.await();

        System.out.println("main thread invoke end");
    }

}
