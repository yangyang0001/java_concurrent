package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_002;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantNonFairLockDemo {

    public static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();

                Thread.currentThread().sleep(1000L);

                lock.unlock();
            }
        }, "threadA");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                System.out.println("threadA into lock running!");

                lock.unlock();
            }
        }, "threadB");

        threadA.start();

        Thread.currentThread().sleep(2000L);

//        threadB.start();

        System.out.println("main thread invoke end!");

    }

}
