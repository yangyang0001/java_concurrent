package com.deepblue.inaction_01_from_introduction_to_mastery.mine_test_chapter_02.example_001;

import java.util.concurrent.Callable;

/**
 *
 */
public class ThreadC implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("ThreadC threadId = " + Thread.currentThread().getId());
        System.out.println("ThreadC getName  = " + Thread.currentThread().getName());
        System.out.println("ThreadC priority = " + Thread.currentThread().getPriority());
        System.out.println("ThreadC state    = " + Thread.currentThread().getState());
        return "HelloWorld";
    }

}
