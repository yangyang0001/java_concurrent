package com.deepblue.inaction_01_from_introduction_to_mastery.mine_test_chapter_02.example_001;

import com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_001.FutureTaskDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        threadA.setName("ThreadA");
        threadA.start();

        Thread threadB = new Thread(new ThreadB());
        threadB.setName("ThreadB");
        threadB.start();

        FutureTask<String> future = new FutureTask<String>(new ThreadC());
        Thread threadC = new Thread(future);
        threadC.setName("ThreadC");
        threadC.start();

        try {
            String result = future.get();
            System.out.println("future task result = " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
