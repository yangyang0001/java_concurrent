package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_00_base.example_003;

public class Main {

    public static void main(String[] args) {
        new PrintThread("Good!").start();
        new PrintThread("Nice!").start();
    }
}
