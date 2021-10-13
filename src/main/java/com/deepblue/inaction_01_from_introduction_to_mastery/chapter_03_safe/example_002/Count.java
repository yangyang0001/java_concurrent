package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_002;

public class Count {

	private int num = 0;

	public synchronized void methodA() {
		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		num += 1;
		System.out.println(Thread.currentThread().getName() + ", num = " + num);
	}

	public void methodB() {
		synchronized (this) {
			try {
				Thread.currentThread().sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			num += 1;
			System.out.println(Thread.currentThread().getName() + ", num = " + num);
		}
	}

}
