package com.deepblue.inaction_03_the_art_of_concurrent_programming.chapter_04_base.example_001;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {

    public static void main(String[] args) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] infos = bean.dumpAllThreads(false, false);

        for(ThreadInfo info : infos) {
            System.out.println("threadId :" + info.getThreadId() + ", threadName :" + info.getThreadName());
        }

    }
}
