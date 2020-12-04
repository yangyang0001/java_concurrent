package com.deepblue.inaction.chapter_03_share.thread_005_ThreadLocal;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadLocalTest {

    public static ThreadLocal<String> threadNameHolder = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                threadNameHolder.set(Thread.currentThread().getName());
                System.out.println("threadNameHolder getName :" + threadNameHolder.get());
            });
            threads[i].setName("thread_name_" + i);
        }
        Arrays.stream(threads).forEach(item -> item.start());

        System.out.println("------------------------------------------------------------------------------------------------------------------------");

        List<String> list = Lists.newArrayList();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        List<List<String>> partition = Lists.partition(list, 2);

        System.out.println(partition.size());
        partition.stream().forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

}
