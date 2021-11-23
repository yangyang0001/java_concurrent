package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_03_guarded_suspension.example_001;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {

    private LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();

    public void putRequest (Request request) {
        queue.add(request);
    }

    public Request getRequest () {
        while(true) {
            if(queue.peek() != null) {
                return queue.poll();
            }
        }
    }
}
