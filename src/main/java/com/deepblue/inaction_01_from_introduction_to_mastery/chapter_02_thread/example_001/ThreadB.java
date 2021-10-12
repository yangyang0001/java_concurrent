package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_001;

public class ThreadB implements Runnable{

    @Override
    public void run() {
        System.out.println("threadB run method invoked begin");

        try {
            Thread.currentThread().sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int count = Thread.activeCount();
        Thread currentThread = Thread.currentThread();
        long id = currentThread.getId();
        String name = currentThread.getName();
        int priority = currentThread.getPriority();
        Thread.State state = currentThread.getState();
        boolean alive = currentThread.isAlive();
        boolean daemon = currentThread.isDaemon();
        ThreadGroup threadGroup = currentThread.getThreadGroup();

        System.out.println("count       :" + count);
        System.out.println("id          :" + count);
        System.out.println("name        :" + name);
        System.out.println("priority    :" + priority);
        System.out.println("state       :" + state);
        System.out.println("alive       :" + alive);
        System.out.println("daemon      :" + daemon);
        System.out.println("threadGroup :" + threadGroup);


        System.out.println("threadB run method invoked end");
    }
}
