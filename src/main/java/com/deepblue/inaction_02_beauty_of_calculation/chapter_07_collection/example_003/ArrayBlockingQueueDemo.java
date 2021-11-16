package com.deepblue.inaction_02_beauty_of_calculation.chapter_07_collection.example_003;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA invoke run add element is zhangsan");
                queue.add("zhangsan");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = queue.poll();
                System.out.println("threadB invoke run poll element is " + result);
            }
        });

        threadA.start();
        threadA.join();

        threadB.start();


    }
}
