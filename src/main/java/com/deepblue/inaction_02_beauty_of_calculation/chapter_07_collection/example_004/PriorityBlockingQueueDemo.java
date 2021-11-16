package com.deepblue.inaction_02_beauty_of_calculation.chapter_07_collection.example_004;

import lombok.*;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {

        PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<User>();

        queue.add(new User("lisi", "234567", 20));
        queue.add(new User("wangwu", "345678", 30));
        queue.add(new User("zhangsan", "123456", 10));

        queue.stream().forEach(System.out::println);

        User user = queue.poll();
        System.out.println(user);


    }


    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class User implements Comparable<User> {

        private String username;

        private String password;

        private int age;

        /**
         * 实现了 age 从大到小 的排列
         */
        @Override
        public int compareTo(User target) {
            if(target.getAge() > this.age) {
                return 1;
            } else {
                return -1;
            }
        }
    }


}
