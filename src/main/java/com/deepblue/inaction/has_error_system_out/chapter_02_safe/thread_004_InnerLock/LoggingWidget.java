package com.deepblue.inaction.has_error_system_out.chapter_02_safe.thread_004_InnerLock;

public class LoggingWidget extends Widget{

    public synchronized void doSomething() {
        System.out.println("LoggingWidget doSomething invoked");
    }
}
