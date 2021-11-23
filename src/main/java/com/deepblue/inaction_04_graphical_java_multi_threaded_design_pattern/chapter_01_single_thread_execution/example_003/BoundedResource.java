package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_01_single_thread_execution.example_003;

import java.util.concurrent.Semaphore;

public class BoundedResource {

    private int permits;

    private Semaphore semaphore;

    public BoundedResource(int permits) {
        this.permits = permits;
        this.semaphore = new Semaphore(permits);
    }

    public void use() {

        try {
            semaphore.acquire();
            doUse();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

    }

    private void doUse() throws InterruptedException {
        System.out.println("BEGIN : used = " + (permits - semaphore.availablePermits()));

        Thread.sleep(1000L);

        System.out.println("END   : used = " + (permits - semaphore.availablePermits()));
    }


}
