package com.deepblue.inaction_02_beauty_of_calculation.chapter_07_collection.example_005;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo0 {

    public static void main(String[] args) {

        DelayQueue<Student> queue = new DelayQueue<Student>();

        long expire0 = System.currentTimeMillis() + 5000;
        long expire1 = System.currentTimeMillis() + 15000;
        long expire2 = System.currentTimeMillis() + 25000;

        queue.add(new Student("zhangsan", "123456", expire1));
        queue.add(new Student("wangwu", "345678", expire2));
        queue.add(new Student("lisi", "234567", expire0));


        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while(queue.size() > 0) {
                    Student student = queue.poll();
                    if(student != null) {
                        System.out.println(JSON.toJSONString(student));
                    }
                }
            }
        });

        threadA.start();
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Student implements Delayed {
        // 用户名
        private String username;
        // 密码
        private String password;
        // 到期时间 毫秒
        private long expire;


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.getExpire() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Student target = (Student) o;
            if(this.getExpire() >= target.getExpire()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
