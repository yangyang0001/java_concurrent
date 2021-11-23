package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_01_single_thread_execution.example_001;

public class Main {

    public static void main(String[] args) {

        Gate gate = new Gate();
        new Thread(new UserThread(gate, "Alice", "Alaska")).start();
        new Thread(new UserThread(gate, "Bobby", "Brazil")).start();
        new Thread(new UserThread(gate, "Chris", "Canada")).start();

    }
}
