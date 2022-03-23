package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_003;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 *
 */
public class CountTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool joinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = joinPool.submit(new CountTask(1, 100));
        System.out.println(submit.get());

    }
}
