package com.deepblue.inaction_01_from_introduction_to_mastery.mine_test_chapter_02.example_001;

/**
 *
 */
public class ThreadB implements Runnable{

    @Override
    public void run() {
        System.out.println("ThreadB threadId = " + Thread.currentThread().getId());
        System.out.println("ThreadB getName  = " + Thread.currentThread().getName());
        System.out.println("ThreadB priority = " + Thread.currentThread().getPriority());
        System.out.println("ThreadB state    = " + Thread.currentThread().getState());
    }
}
