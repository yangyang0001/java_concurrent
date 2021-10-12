package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_002;

import com.alibaba.fastjson.JSON;

public class Client {

    public static void main(String[] args) {

        System.out.println("main thread begin");

        for (int i = 0; i < 5; i++) {
            ThreadB threadB = new ThreadB();
            new Thread(threadB, "threadB-" + i).start();
        }

        Thread thread = Thread.currentThread();
        long id = thread.getId();
        String name = thread.getName();
        int priority = thread.getPriority();
        Thread.State state = thread.getState();
        boolean alive = thread.isAlive();
        boolean daemon = thread.isDaemon();

        System.out.println("main thread id       = " + id);
        System.out.println("main thread name     = " + name);
        System.out.println("main thread priority = " + priority);
        System.out.println("main thread state    = " + state);
        System.out.println("main thread alive    = " + alive);
        System.out.println("main thread daemon   = " + daemon);

        System.out.println("main thread end");
    }
}
