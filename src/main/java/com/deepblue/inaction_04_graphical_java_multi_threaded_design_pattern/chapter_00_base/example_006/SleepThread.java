package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_00_base.example_006;

public class SleepThread {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000L);

            Thread thread = new Thread();
            thread.sleep(1000);
            System.out.println("Good!");
        }

    }
}
