package com.deepblue.inaction_01_from_introduction_to_mastery.mine_test_chapter_02.example_001;

/**
 *
 */
public class ThreadA extends Thread {

    @Override
    public void run() {
        System.out.println("ThreadA threadId = " + Thread.currentThread().getId());
        System.out.println("ThreadA getName  = " + Thread.currentThread().getName());
        System.out.println("ThreadA priority = " + Thread.currentThread().getPriority());
        System.out.println("ThreadA state    = " + Thread.currentThread().getState());
    }
}
