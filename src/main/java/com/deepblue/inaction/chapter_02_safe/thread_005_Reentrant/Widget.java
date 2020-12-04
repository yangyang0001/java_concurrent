package com.deepblue.inaction.chapter_02_safe.thread_005_Reentrant;

public class Widget {
    public synchronized void doSomething() {
        System.out.println("Widget doSomething ...");
    }
}
