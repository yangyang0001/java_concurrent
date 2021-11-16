package com.deepblue.inaction_02_beauty_of_calculation.chapter_07_collection.example_002;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread threadA = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                System.out.println("threadA invoke queue add element is zhangsan");
                queue.put("zhangsan");
                queue.put("zhangsan");
                queue.put("zhangsan");
                queue.put("zhangsan");
            }

        });

        Thread threadB = new Thread(new Runnable() {

            @Override
            public void run() {
                String result = queue.poll();
                System.out.println("threadB invoke pool element is " + result);
            }

        });

        Thread threadC = new Thread(new Runnable() {

            @Override
            public void run() {
                queue.remove("zhangsan");
                System.out.println("threadC invoke remove element zhangsan, queue.size is " + queue.size());
            }

        });

        threadA.start();
        threadA.join();

        threadB.start();
        threadB.join();

        threadC.start();
    }
}
