package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_004;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 *
 */
public class FolderTask extends RecursiveTask {

    private File root;

    String prefix = "";

    public FolderTask(File root, String prefix) {
        this.root = root;
        this.prefix = prefix;
    }

    @Override
    protected Object compute() {

        if(root.isFile()) {
            System.out.println(prefix + root.getName());
        } else {
            System.out.println(prefix + root.getName());
            prefix += "\t";
            List<File> files = Arrays.asList(root.listFiles());
            Collections.sort(files);
            for(File file : files) {
                FolderTask task = new FolderTask(file, prefix);
                task.fork();
                task.join();
            }
        }

        return null;
    }
}
