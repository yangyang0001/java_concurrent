package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_004;

public class ThreadB implements Runnable {

	private Count count;

	public ThreadB(Count count) {
		this.count = count;
	}

	@Override
	public void run() {
		count.set();
	}
}
