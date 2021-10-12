package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_005;

import com.alibaba.fastjson.JSON;

public class Client {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();
        ThreadGroup threadGroup = thread.getThreadGroup();

        Thread threadA = new Thread(new ThreadA(), "threadA");
        Thread threadB = new Thread(new ThreadA(), "threadB");
        Thread threadC = new Thread(new ThreadA(), "threadC");

        threadA.start();
        threadB.start();
        threadC.start();

        ThreadGroup parent = threadGroup.getParent();
        System.out.println("threadGroup active count is :" + threadGroup.activeCount());
        System.out.println("threadGroup to json is      :" + JSON.toJSONString(threadGroup));
        System.out.println("threadGroup parent is       :" + JSON.toJSONString(parent));

    }
}
