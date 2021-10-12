package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_007;

public class Client {

    public static void main(String[] args) {

        Thread threadA = new Thread(new ThreadA(), "threadA");
        threadA.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        threadA.start();

    }
}
