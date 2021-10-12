package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_006;

public class Client {

    public static void main(String[] args) {

        ThreadLocalHolder holder = new ThreadLocalHolder();
        Thread threadA = new Thread(new ThreadA(holder));
        Thread threadB = new Thread(new ThreadA(holder));
        Thread threadC = new Thread(new ThreadA(holder));

        threadA.start();
        threadB.start();
        threadC.start();

    }
}
