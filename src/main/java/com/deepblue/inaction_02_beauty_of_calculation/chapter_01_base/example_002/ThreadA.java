package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_002;

public class ThreadA implements Runnable {

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " running invoked with runnable");
	}

}
