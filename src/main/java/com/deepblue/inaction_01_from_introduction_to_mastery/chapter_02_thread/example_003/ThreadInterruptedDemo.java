package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_003;

import lombok.extern.slf4j.Slf4j;

public class ThreadInterruptedDemo implements Runnable{

    @Override
    public void run() {

        long start = System.currentTimeMillis();

        while (true) {

            System.out.println("interrupted thread running time = " + System.currentTimeMillis());
            if(System.currentTimeMillis() - start > 20000) {
                break;
            }

            if(Thread.currentThread().isInterrupted()) {
                break;
            }

        }

        System.out.println("interrupted thread exiting");
    }
}
