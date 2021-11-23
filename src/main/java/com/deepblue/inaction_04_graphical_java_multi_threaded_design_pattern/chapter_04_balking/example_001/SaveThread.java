package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_04_balking.example_001;

import lombok.SneakyThrows;

public class SaveThread implements Runnable {

    private Data data;

    public SaveThread(Data data) {
        this.data = data;
    }

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            data.save();
            Thread.sleep(1000L);
        }
    }
}
