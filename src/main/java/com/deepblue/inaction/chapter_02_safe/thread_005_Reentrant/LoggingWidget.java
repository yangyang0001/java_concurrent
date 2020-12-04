package com.deepblue.inaction.chapter_02_safe.thread_005_Reentrant;

public class LoggingWidget extends Widget {

    @Override
    public synchronized void doSomething() {
        System.out.println("LoggingWidget doSomething ...");
        super.doSomething();
    }
}
