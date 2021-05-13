package com.deepblue.inaction.chapter_02_safe.thread_004_InnerLock;

public class LoggingWidget extends Widget{

    public synchronized void doSomething() {
        System.out.println("LoggingWidget doSomething invoked");
    }
}
