package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_007;

public class SleepMonitorDemo {

	private static final Object obj = new Object();

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					System.out.println("threadA begin run");

					try {
						Thread.currentThread().sleep(10000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("threadA  end  run");
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					System.out.println("threadB begin run");

					try {
						Thread.currentThread().sleep(5000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("threadB  end  run");
				}
			}
		});

		threadA.start();
		threadB.start();

		System.out.println("thread main invoke end");
	}
}
