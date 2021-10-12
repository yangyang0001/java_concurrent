package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_007;

public class ThreadA implements Runnable{

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        for(int i = 0; i < 100; i++) {
            System.out.println("threadId is " + thread.getId() + ", threadName is " + thread.getName() + " i = " + i);
            try {
                Thread.currentThread().sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 10) {
                int result = 10 / 0;
            }
        }
    }
}
