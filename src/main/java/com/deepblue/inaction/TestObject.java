package com.deepblue.inaction;

import com.google.common.collect.Lists;

import java.util.List;

public class TestObject {
    public static void main(String[] args) {
        System.out.println("Hello Concurrent");

        List<Integer> list = Lists.newArrayList();
        list.add(100);
        list.add(101);
        list.add(102);

        list.stream().forEach(System.out::println);
    }
}
