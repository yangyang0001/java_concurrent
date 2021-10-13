package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_006;

public class ThreadA implements Runnable {

	private Count count;

	public ThreadA(Count count) {
		this.count = count;
	}

	@Override
	public void run() {
		count.add();
	}
}
