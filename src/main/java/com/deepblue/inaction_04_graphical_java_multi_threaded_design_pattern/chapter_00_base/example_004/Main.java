package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_00_base.example_004;

public class Main {

    public static void main(String[] args) {

        Thread threadA = new Thread(new PrintThread("Good!"));
        Thread threadB = new Thread(new PrintThread("Nice!"));

        threadA.start();
        threadB.start();

    }
}
