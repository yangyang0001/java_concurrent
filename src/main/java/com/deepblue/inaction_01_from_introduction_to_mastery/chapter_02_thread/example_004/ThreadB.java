package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_004;

public class ThreadB extends Thread{

    @Override
    public void run() {
        System.out.println("threadB running begin");
        for(int i = 0; i < 100000; i++) {
            try {
                Thread.currentThread().sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadB running i = " + i);
        }
        System.out.println("threadB running end");
    }
}
