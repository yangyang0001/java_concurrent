package com.deepblue.inaction_03_the_art_of_concurrent_programming.chapter_02_principle.example_002;


import com.deepblue.inaction_02_beauty_of_calculation.chapter_11_action.example_002.ThreadFactoryDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class CountDemo {

    public Long count0 = 0L;

    public AtomicLong count1 = new AtomicLong(0);


    public static void main(String[] args) throws InterruptedException {

        CountDemo demo = new CountDemo();

        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 1000; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    demo.unsafeCount();
                }
            });
        }

        for(int i = 0; i < 1000; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    demo.safeCount();
                }
            });
        }

        Thread.currentThread().sleep(2000L);

        service.shutdown();

        System.out.println("demo.count0 = " + demo.count0);
        System.out.println("demo.count1 = " + demo.count1);

    }

    public void unsafeCount() {
        count0 ++;
    }

    public void safeCount() {
        count1.incrementAndGet();
    }


}
