package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_001;

import java.util.concurrent.Callable;

public class SonTaskB implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " call invoke start");

		thread.sleep(1000L);

		System.out.println(thread.getName() + " call invoke end  ");

		return "BBBBBBBB";
	}
}
