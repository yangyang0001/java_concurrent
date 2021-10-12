package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_001;

public class ThreadA extends Thread{

    @Override
    public void run() {

        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("threadA run method invoked");
    }

}
