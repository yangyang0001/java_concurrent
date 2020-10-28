package com.deepblue.inaction.thread_001_UnSafeSequence;

import lombok.Builder;
import lombok.Data;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.PrintStream;

@NotThreadSafe
public class Thread_001_UnSafeSequence {

    public static void main(String[] args) {

        UnSafeSequence unSafeSequence = new UnSafeSequence();

        Thread thread1 = new Thread(() -> {
            for(;;) {
                int value = unSafeSequence.getNext();
                System.out.println(Thread.currentThread().getName() + "运行value:" + value);
                if(value >= 100) {
                    break;
                }
            }
        });
        thread1.setName("first");

        Thread thread2 = new Thread(() -> {
            for(;;) {
                int value = unSafeSequence.getNext();
                System.out.println(Thread.currentThread().getName() + "运行value:" + value);
                if(value >= 100) {
                    break;
                }
            }
        });
        thread2.setName("secon");

        Thread thread3 = new Thread(() -> {
            for(;;) {
                int value = unSafeSequence.getNext();
                System.out.println(Thread.currentThread().getName() + "运行value:" + value);
                if(value >= 100) {
                    break;
                }
            }
        });
        thread3.setName("third");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
