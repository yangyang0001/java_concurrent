package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_02_immutable.example_001;

public class Main {

    public static void main(String[] args) {

        new PrintPersonThread(new Person("Alice", "Alaska")).start();
        new PrintPersonThread(new Person("Bobby", "Brazil")).start();
        new PrintPersonThread(new Person("Chris", "Canada")).start();

    }
}
