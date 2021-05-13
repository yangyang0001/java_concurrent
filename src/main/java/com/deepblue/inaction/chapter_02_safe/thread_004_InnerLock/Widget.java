package com.deepblue.inaction.chapter_02_safe.thread_004_InnerLock;

public class Widget {

    public synchronized void doSomething() {
        System.out.println("Widget doSomething invoked");
    }
}
