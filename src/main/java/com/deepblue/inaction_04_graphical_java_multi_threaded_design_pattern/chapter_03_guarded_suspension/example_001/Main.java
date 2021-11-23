package com.deepblue.inaction_04_graphical_java_multi_threaded_design_pattern.chapter_03_guarded_suspension.example_001;

public class Main {

    public static void main(String[] args) {

        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue).start();
        new ServerThread(requestQueue).start();

    }
}
