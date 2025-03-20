package com.deepblue.inaction_01_from_introduction_to_mastery.mine_test_chapter_02.example_002;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class MineThread implements Runnable {
    @Override
    public void run() {

        try {
            Thread.currentThread().sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " run invoked begin");
        long id = thread.getId();
        String name = thread.getName();
        int priority = thread.getPriority();
        Thread.State state = thread.getState();
        boolean alive = thread.isAlive();
        boolean daemon = thread.isDaemon();
        System.out.println(thread.getName() + " id          = " + id);
        System.out.println(thread.getName() + " name        = " + name);
        System.out.println(thread.getName() + " priority    = " + priority);
        System.out.println(thread.getName() + " state       = " + state);
        System.out.println(thread.getName() + " alive       = " + alive);
        System.out.println(thread.getName() + " daemon      = " + daemon);
        ThreadGroup threadGroup = thread.getThreadGroup();
        System.out.println(thread.getName() + " threadGroup = " + JSON.toJSONString(threadGroup));
        System.out.println(thread.getName() + " activeCount = " + threadGroup.activeCount());
        System.out.println(thread.getName() + " run invoked end");

    }
}
