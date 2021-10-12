package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_005;

public class ThreadA implements Runnable{

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println(name + " running invoke start");
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " i = " + i);
            try {
                Thread.currentThread().sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " running invoke end");
    }
}
