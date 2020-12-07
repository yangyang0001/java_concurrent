package com.deepblue.inaction.chapter_03_share.thread_005_ThreadLocal;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 可以将ThreadLocal 看作 Map<Thread, Object> 的存储类型来看, 它存储了和线程相关的数据, 但是事实上并不是这样的 与线程相关的数据存储在线程对象自身中, 线程终止后这些值会被回收
 */
public class ThreadLocalTest {

    public static ThreadLocal<String> threadNameHolder = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println("thread.name is :" + Thread.currentThread().getName());
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
