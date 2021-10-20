package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_09_monitor.example_002;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LinuxDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 10; i++) {
			Thread thread = new Thread(new ThreadA(), "threadA_" + i);
			executor.execute(thread);
		}

		executor.shutdown();
		System.out.println("thread main invoked end");
	}

}
