package com.deepblue.inaction.has_error_system_out.chapter_02_safe.thread_005_Reentrant;

public class Widget {
    public synchronized void doSomething() {
        System.out.println("Widget doSomething ...");
    }
}
