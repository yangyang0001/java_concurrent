package com.deepblue.inaction_03_the_art_of_concurrent_programming.chapter_01_import.example_001;

/**
 * concurrency cost time = 1121150, b = -10000
 * serial      cost time = 242635,  b = -10000
 *
 * concurrency cost time = 944590, b = -10000
 * serial      cost time = 233762, b = -10000
 *
 * concurrency cost time = 1787545, b = -10000
 * serial      cost time = 264483,  b = -10000
 */
public class ConcurrentTest {

    private static final long count = 10000L;


    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    public static void concurrency() throws InterruptedException {
        long start = System.nanoTime();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();

        int b = 0;
        for (int i = 0; i < count; i++) {
            b --;
        }

        thread.join();

        long time = System.nanoTime() - start;

        System.out.println("concurrency cost time = " + time + ", b = " + b);
    }

    public static void serial() {
        long start = System.nanoTime();

        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (int i = 0; i < count; i++) {
            b --;
        }

        long time = System.nanoTime() - start;

        System.out.println("serial      cost time = " + time + ", b = " + b);
    }
}
