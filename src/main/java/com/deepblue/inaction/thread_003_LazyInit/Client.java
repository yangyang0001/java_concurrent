package com.deepblue.inaction.thread_003_LazyInit;

public class Client {

    public static void main(String[] args) {
        SafeSingleton.User base = SafeSingleton.getInstance();

        Thread[] threads = new Thread[10];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                SafeSingleton.User user = SafeSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + ":" + (base == user));
            });
            threads[i].setName("thread_" + i);
        }

        for(int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
