package com.deepblue.inaction_02_beauty_of_calculation.chapter_09_valve.example_002;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo1 {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("parent thread invoke running");
            }
        });

        ExecutorService service = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 2; i++) {

            int threadNo = i;

            service.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(5000L);
                    System.out.println("child thread" + threadNo + " invoke running");
                    barrier.await();
                }
            });

        }

        service.shutdown();
    }
}
