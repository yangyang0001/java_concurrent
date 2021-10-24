package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_003;

import java.util.concurrent.Callable;

public class ThreadA implements Callable<String> {

	@Override
	public String call() {

		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " call invoke start ...");

		try {
			thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(thread.getName() + " call invoke end   ...");

		return "hello world";
	}

}
