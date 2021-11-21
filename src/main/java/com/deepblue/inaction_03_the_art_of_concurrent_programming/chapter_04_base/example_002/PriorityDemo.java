package com.deepblue.inaction_03_the_art_of_concurrent_programming.chapter_04_base.example_002;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 程序验证结果, 程序优先级 不会 当做 内核线程的参数进行分配CPU资源, 内核线程默认的优先级为5
 */
public class PriorityDemo {

    private static volatile boolean noStart = true;
    private static volatile boolean noEnd = true;

    public static void main(String[] args) throws InterruptedException {

        List<Job> jobs = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "thread_" + i);
            thread.setPriority(priority);
            thread.start();
        }

        noStart = false;
        noEnd   = false;
        Thread.currentThread().sleep(1000L);

        for(Job job : jobs) {
            System.out.println(JSON.toJSONString(job));
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    static class Job implements Runnable {

        private long priority;
        private long count;

        public Job(long priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            System.out.println("thread name is " + Thread.currentThread().getName() + ", thread priority is " + Thread.currentThread().getPriority());
            while(noStart) {
                Thread.yield();
            }

            while(noEnd) {
                Thread.yield();
                count ++;
            }
        }
    }
}
