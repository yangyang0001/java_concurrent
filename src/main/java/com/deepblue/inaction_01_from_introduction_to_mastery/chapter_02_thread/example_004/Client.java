package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_004;

public class Client {

    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadB.setDaemon(true);

        threadA.start();
        threadB.start();

    }
}
