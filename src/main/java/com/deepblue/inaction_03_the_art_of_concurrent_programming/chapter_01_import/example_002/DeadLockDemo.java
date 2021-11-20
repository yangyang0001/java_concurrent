package com.deepblue.inaction_03_the_art_of_concurrent_programming.chapter_01_import.example_002;

import lombok.SneakyThrows;

public class DeadLockDemo {

    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                synchronized (A) {
                    Thread.currentThread().sleep(2000L);
                    synchronized (B) {
                        System.out.println("threadA invoke running");
                    }
                }
            }

        });

        Thread threadB = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                synchronized (B) {
                    Thread.currentThread().sleep(2000L);
                    synchronized (A) {
                        System.out.println("threadB invoke running");
                    }
                }
            }

        });

        threadA.start();
        threadB.start();

    }
}
