package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_001;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallClient {

    public static void main(String[] args) {

        ThreadC threadC = new ThreadC();
        FutureTask<String> future = new FutureTask<String>(threadC);
        new Thread(future).start();
        System.out.println("主线程Begin ...");

        try {
            String result = future.get();
            System.out.println("result = " + JSON.toJSONString(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("主线程End   ...");
    }
}
