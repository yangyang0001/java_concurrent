package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_03_guarded_suspension.example_001;

import lombok.SneakyThrows;

import java.util.concurrent.ThreadLocalRandom;

public class ServerThread extends Thread {

    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @SneakyThrows
    @Override
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for(int i = 0; i < 10; i++) {
            Request request = requestQueue.getRequest();
            System.out.println("server get request = " + request);
        }
    }
}
