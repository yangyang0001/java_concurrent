package com.deepblue.inaction_02_beauty_of_calculation.chapter_09_valve.example_003;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo0 {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 10; i++) {

            int threadNo = i;

            service.execute(new Runnable() {

                @SneakyThrows
                @Override
                public void run() {
                    // 获取信号量
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(2000L);

                    // 释放信号量
                    semaphore.release();
                }

            });
        }

        service.shutdown();

    }
}
