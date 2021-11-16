package com.deepblue.inaction_02_beauty_of_calculation.chapter_09_valve.example_002;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo0 {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("parent thread invoke end");
            }
        });

        Thread threadA = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000L);
                System.out.println("threadA invoke running");
                barrier.await();
            }

        });

        Thread threadB = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(5000L);
                System.out.println("threadB invoke running");
                barrier.await();
            }

        });

        threadA.start();
        threadB.start();
    }
}
