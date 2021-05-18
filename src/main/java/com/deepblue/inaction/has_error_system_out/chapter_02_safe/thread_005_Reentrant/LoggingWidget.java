package com.deepblue.inaction.has_error_system_out.chapter_02_safe.thread_005_Reentrant;

public class LoggingWidget extends Widget {

    @Override
    public synchronized void doSomething() {
        System.out.println("LoggingWidget doSomething ...");
        super.doSomething();
    }
}
