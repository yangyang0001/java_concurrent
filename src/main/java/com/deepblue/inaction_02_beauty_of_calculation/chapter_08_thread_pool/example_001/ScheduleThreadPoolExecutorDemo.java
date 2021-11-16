package com.deepblue.inaction_02_beauty_of_calculation.chapter_08_thread_pool.example_001;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA invoke running, current time is " + System.currentTimeMillis());
            }
        });

        /**
         * 只执行一次的延迟任务
         */
//        long delay = 10000L;
//        ScheduledFuture<?> schedule = service.schedule(threadA, delay, TimeUnit.MILLISECONDS);

        /**
         * 首次执行定时延迟 + 后续相同间隔延迟 任务
         */
//        long initialDelay = 10000L;
//        long delay = 2000L;
//        service.scheduleWithFixedDelay(threadA, initialDelay, delay, TimeUnit.MILLISECONDS);

        /**
         * 首次执行定时延迟 + 后续执行间隔相同的延迟 任务
         */
        long initialDelay = 10000L;
        long period = 2000L;
        service.scheduleAtFixedRate(threadA, initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
