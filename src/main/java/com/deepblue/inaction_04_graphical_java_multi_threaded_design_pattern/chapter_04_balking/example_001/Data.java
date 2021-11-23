package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_04_balking.example_001;

import java.io.FileWriter;
import java.io.IOException;

public class Data {

    private final String fileName;

    private String content;

    private boolean changed;

    public Data(String fileName, String content, boolean changed) {
        this.fileName = fileName;
        this.content = content;
        this.changed = changed;
    }


    public synchronized void save() {
        if(!changed) {
            return;
        }
        doSave();
        changed = false;
    }


    public synchronized void change(String content) {
        this.content = content;
        changed = true;
    }


    public void doSave() {

        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);

        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
