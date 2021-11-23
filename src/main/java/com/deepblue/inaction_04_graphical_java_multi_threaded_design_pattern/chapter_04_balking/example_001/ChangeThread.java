package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_04_balking.example_001;

import lombok.SneakyThrows;

public class ChangeThread implements Runnable{

    private Data data;

    public ChangeThread(Data data) {
        this.data = data;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            data.change("NO." + i);
            Thread.sleep(1000L);
            data.save();
        }
    }
}
