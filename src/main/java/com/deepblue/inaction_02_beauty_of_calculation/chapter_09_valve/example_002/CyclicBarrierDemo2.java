package com.deepblue.inaction_02_beauty_of_calculation.chapter_09_valve.example_002;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO 书中的例子, CyclicBarrier 在使用完成之后, 能够重复使用!
 */
public class CyclicBarrierDemo2 {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2);

        ExecutorService service = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 2; i++) {

            int threadNo = i;

            service.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println("thread" + threadNo + " running part one");

                    barrier.await();
                    System.out.println("thread" + threadNo + " running part two");

                    barrier.await();
                    System.out.println("thread" + threadNo + " running part three");
                }
            });
        }

        service.shutdown();

    }
}
