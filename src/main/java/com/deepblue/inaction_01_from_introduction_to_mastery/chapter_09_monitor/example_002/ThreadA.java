package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_09_monitor.example_002;

public class ThreadA implements Runnable {

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " running start");
		try {
			thread.sleep(100000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thread.getName() + " running end");
	}
}
