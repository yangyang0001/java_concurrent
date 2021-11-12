package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_003;

import org.bouncycastle.crypto.engines.RC2Engine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionDemo {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("begin await");

                    // 基于当前的条件变量 进行阻塞 等待相同条件变量的 signal() 方法的唤醒 才能继续执行
                    condition.await();

                    System.out.println("end   await");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "threadA");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("begin signal");

                    Thread.currentThread().sleep(2000L);

                    condition.signal();

                    System.out.println("end   signal");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "threadB");

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread invoke end");
    }
}
