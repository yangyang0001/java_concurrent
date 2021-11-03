package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_002;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadA implements Runnable {

	private AtomicInteger count;

	private CountDownLatch cdl;

	public ThreadA() {}

	public ThreadA(AtomicInteger count, CountDownLatch cdl) {
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
		int result = count.incrementAndGet();
		System.out.println(thread.getName() + " result is " + result);
	}


}
