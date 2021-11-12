package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_004;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

public class NonReentrantLockDemo {

    public static NonReentrantLock lock = new NonReentrantLock();
    public static Condition producerCondition = lock.newCondition();
    public static Condition consumerCondition = lock.newCondition();

    public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    public static Integer size = 10;

    public static void main(String[] args) {

        Thread consumer0 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();
                while(queue.size() == 0) {
                    consumerCondition.await();
                }
                try {
                    String result = queue.take();
                    System.out.println(Thread.currentThread().getName() + " result = " + result + ", queue.size = " + queue.size());
                    producerCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "consumer0");

        Thread consumer1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();
                while(queue.size() == 0) {
                    consumerCondition.await();
                }
                try {
                    String result = queue.take();
                    System.out.println(Thread.currentThread().getName() + " result = " + result + ", queue.size = " + queue.size());
                    producerCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "consumer1");

        Thread producer0 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();
                while(queue.size() == size) {
                    producerCondition.await();
                }
                try {
                    queue.add("element");
                    System.out.println(Thread.currentThread().getName() + " add element, queue.size = " + queue.size());
                    consumerCondition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "producer0");

        Thread producer1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();
                while(queue.size() == size) {
                    producerCondition.await();
                }
                try {
                    queue.add("element");
                    System.out.println(Thread.currentThread().getName() + " add element, queue.size = " + queue.size());
                    consumerCondition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "producer1");

        consumer0.start();
        consumer1.start();
        producer0.start();
        producer1.start();

    }
}
