package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_004;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 *
 */
public class FolderTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool joinPool = new ForkJoinPool();

        File file = new File("/Users/yangjianwei/IdeaProjects/java_concurrent/src/main/java/com/deepblue");
        String prefix = "";
        FolderTask task = new FolderTask(file, prefix);

        ForkJoinTask submit = joinPool.submit(task);
        submit.get();

    }
}
