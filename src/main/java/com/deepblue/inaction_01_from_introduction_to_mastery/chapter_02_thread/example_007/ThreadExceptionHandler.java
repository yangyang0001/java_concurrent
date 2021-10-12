package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_007;

import com.alibaba.fastjson.JSON;

public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("thread exception handler execute start ....");
        System.out.println("threadId is " + t.getId() + ", threadName is " + t.getName());
        System.out.println("exception is " + JSON.toJSONString(e));
        System.out.println("thread exception handler execute end   ....");
    }
}
