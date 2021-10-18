package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_06_thread_pool.example_004;

public class ThreadA implements Runnable {

	private Integer num;

	public ThreadA(Integer num) {
		this.num = num;
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " running start num = " + num);
		try {
			Thread.sleep(60000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thread.getName() + " running end   num = " + num);
	}
}
