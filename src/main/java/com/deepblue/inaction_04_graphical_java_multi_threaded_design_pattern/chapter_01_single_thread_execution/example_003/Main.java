package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_01_single_thread_execution.example_003;

public class Main {

    public static void main(String[] args) {

        BoundedResource resource = new BoundedResource(3);

        for (int i = 0; i < 10; i++) {
            new Thread(new UserThread(resource)).start();
        }

    }

}
