package com.deepblue.inaction_02_beauty_of_calculation.chapter_07_collection.example_005;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo1 {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Task> queue = new DelayQueue<>();

        long expire0 = System.currentTimeMillis() + 10000;
        long expire1 = System.currentTimeMillis() + 15000;
        long expire2 = System.currentTimeMillis() + 20000;

        queue.add(new Task("task0", expire2));
        queue.add(new Task("task1", expire0));
        queue.add(new Task("task2", expire1));

        while(queue.size() > 0) {
            Task task = queue.take();
            if(task != null) {
                System.out.println(JSON.toJSONString(task));
            }
        }
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Task implements Delayed {

        // 任务名称
        private String name;
        // 到期时间 毫秒
        private long expire;


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.getExpire() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Task target = (Task) o;

            if(this.expire >= target.getExpire()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
