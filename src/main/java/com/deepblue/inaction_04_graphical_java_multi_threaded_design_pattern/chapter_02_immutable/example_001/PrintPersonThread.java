package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_02_immutable.example_001;

public class PrintPersonThread extends Thread {

    private Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {

        while(true) {
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }

    }
}
