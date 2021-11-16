package com.deepblue.inaction_02_beauty_of_calculation.chapter_07_collection.example_001;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA add element is zhangsan");
                queue.add("zhangsan");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = queue.poll();
                System.out.println("threadB poll element is " + result);
            }
        });

        threadA.start();
        threadA.join();

        threadB.start();

    }
}
