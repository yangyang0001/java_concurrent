package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_007;

import java.util.concurrent.Semaphore;

public class ThreadA implements Runnable {

	private Semaphore semaphore;

	public ThreadA(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + " run start");
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " run end");

		semaphore.release();
	}
}
