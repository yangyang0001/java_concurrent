package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_001;

/**
 * 常用的线程启动方式
 */
public class Client {

    public static void main(String[] args) {

        // 线程A
        ThreadA threadA = new ThreadA();
        threadA.start();

        // 线程B
        Thread  threadB = new Thread(new ThreadB());
        threadB.start();

        System.out.println("主线程启动");
    }

}
