package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_006;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    public static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static ReentrantReadWriteLock.ReadLock readLock  = readWriteLock.readLock();
    public static ReentrantReadWriteLock.ReadLock writeLock = readWriteLock.readLock();



    public static List<String> array = Lists.newArrayList();

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + "  invoke run array.size = " + array.size());
                readLock.unlock();
            }
        }, "read0");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + "  invoke run array.size = " + array.size());
                readLock.unlock();
            }
        }, "read1");

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                writeLock.lock();
                array.add("element");
                System.out.println(Thread.currentThread().getName() + " invoke run array.size = " + array.size());
                writeLock.unlock();

            }
        }, "write0");

        threadC.start();
        threadA.start();
        threadB.start();

    }

}
