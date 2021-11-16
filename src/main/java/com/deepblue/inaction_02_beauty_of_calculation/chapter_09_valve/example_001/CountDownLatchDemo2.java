package com.deepblue.inaction_02_beauty_of_calculation.chapter_09_valve.example_001;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(2);

        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000L);
                System.out.println("threadA invoke running");
                cdl.countDown();
            }
        });

        service.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000L);
                System.out.println("threadB invoke running");
                cdl.countDown();
            }
        });

        // 等待子线程执行完成 主线程再继续执行
        cdl.await();

        System.out.println("main thread invoke end");

        service.shutdown();
    }
}
