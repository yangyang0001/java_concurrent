package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_009;

public class InterruptedSleepDemo {

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA will sleep 2000s start");
				try {
					Thread.currentThread().sleep(2000000L);
				} catch (InterruptedException e) {
					System.out.println("threadA is interrupted while sleepping");
					return;
				}

				System.out.println("threadA run method invoke end");
			}
		});

		threadA.start();

		Thread.currentThread().sleep(1000);

		threadA.interrupt();

		threadA.join();

		System.out.println("thread main mehtod invoke end");

	}
}
