package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_04_balking.example_001;

public class Main {

    public static void main(String[] args) {

        Data data = new Data("/Users/yangjianwei/IdeaProjects/java_concurrent/src/main/java/com/deepblue/inaction_04_graphical_java_multi_threaded_design_pattern/chapter_04_balking/example_001/balking.txt", "content", false);
        new Thread(new SaveThread(data)).start();
        new Thread(new ChangeThread(data)).start();

    }
}
