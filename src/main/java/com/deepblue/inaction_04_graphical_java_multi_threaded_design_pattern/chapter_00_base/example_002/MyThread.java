package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_00_base.example_002;


public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("Nice!");
        }
    }
}
