package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_00_base.example_005;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {

    public static void main(String[] args) {

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        Thread threadA = threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread().getName() + " Good!");
                }
            }
        });

        Thread threadB = threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread().getName() + " Nice!");
                }
            }
        });

        threadA.start();
        threadB.start();

    }
}
