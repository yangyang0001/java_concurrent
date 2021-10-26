package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_007;

public class SleepDemo {

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA begin run!");

				try {
					Thread.currentThread().sleep(5000L);
				} catch (InterruptedException e) {
					System.out.println("threadA " + e);
				}
			}
		});

		threadA.start();

		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadA.interrupt();
	}
}
