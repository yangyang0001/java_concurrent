package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_001;

import java.util.concurrent.Callable;

public class ThreadC implements Callable {

    @Override
    public String call() throws Exception {
        Thread.currentThread().sleep(1000);
        System.out.println("threadC call method invoked");
        return "threadC invoked";
    }
}
