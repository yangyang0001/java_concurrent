package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_003;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadInterruptedDemo(), "interrupted thread");
        System.out.println("Starting thread ...");

        thread.start();
        Thread.sleep(3000);

        System.out.println("Interrupted thread ...");
        thread.interrupt();
        System.out.println("线程是否中断 :" + thread.isInterrupted());

        thread.sleep(3000);
        System.out.println("Stopping application ...");
    }
}
