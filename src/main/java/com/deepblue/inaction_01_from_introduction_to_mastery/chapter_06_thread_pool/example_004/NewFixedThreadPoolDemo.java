package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_06_thread_pool.example_004;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for(int i = 0; i < 20; i++) {
			Thread thread = new Thread(new ThreadA(i), "threadA_" + i);
			executor.execute(thread);
		}

		executor.shutdown();

		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("thread main running end");
	}
}
