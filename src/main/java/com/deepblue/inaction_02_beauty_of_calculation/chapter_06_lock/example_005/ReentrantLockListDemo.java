package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_005;

public class ReentrantLockListDemo {

    public static void main(String[] args) {

        ReentrantLockList list = new ReentrantLockList();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " add element zhangsan");
                list.add("zhangsan");
            }
        }, "threadA");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " add element lisi");
                list.add("lisi");
            }
        }, "threadB");

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " remove element zhangsan");
                list.remove("zhangsan");
            }
        }, "threadC");

        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = list.get(0);
                System.out.println(Thread.currentThread().getName() + " result = " + result);
            }
        }, "threadD");

        threadA.start();
        threadB.start();
        threadC.start();

        try {
            Thread.currentThread().sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadD.start();

    }
}
