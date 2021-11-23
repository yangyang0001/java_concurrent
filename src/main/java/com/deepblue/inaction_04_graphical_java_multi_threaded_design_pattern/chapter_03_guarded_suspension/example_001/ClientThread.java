package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_03_guarded_suspension.example_001;

import lombok.SneakyThrows;

import java.util.concurrent.ThreadLocalRandom;

public class ClientThread extends Thread {

    private RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @SneakyThrows
    @Override
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for(int i = 0; i < 10; i++) {
            int rand = random.nextInt(1000);
            Request request = new Request("NO." + rand);
            System.out.println("client put request = " + request);
            requestQueue.putRequest(request);
            Thread.sleep(1000L);
        }
    }
}
