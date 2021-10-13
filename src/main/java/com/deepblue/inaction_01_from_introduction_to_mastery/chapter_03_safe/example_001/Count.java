package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_001;

public class Count {
	public int num = 0;

	public void add() {
		try {
			Thread.currentThread().sleep(51);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		num += 1;
		System.out.println(Thread.currentThread().getName() + ", num = " + num);
	}
}
