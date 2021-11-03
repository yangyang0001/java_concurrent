package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_001;

import java.util.concurrent.CountDownLatch;

public class ThreadA implements Runnable {

	private Integer count;

	private CountDownLatch cdl;

	public ThreadA(Integer count, CountDownLatch cdl) {
		this.count = count;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread thread = Thread.currentThread();
		increment();
		int result = get();
		System.out.println(thread.getName() + " result is " + result);
	}

	public void increment() {
		count ++;
	}

	public int get() {
		return count;
	}

}
