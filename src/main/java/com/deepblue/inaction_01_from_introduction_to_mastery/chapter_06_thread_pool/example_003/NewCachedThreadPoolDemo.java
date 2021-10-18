package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_06_thread_pool.example_003;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		for(int i = 0; i < 20; i++) {
			Thread thread = new Thread(new ThreadA(i), "threadA_" + i);
			executor.execute(thread);
		}

		executor.shutdown();

		System.out.println("thread main running end");
	}
}
