package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_003;

public class ThreadB implements Runnable {

	private ReentrantLockDemo demo;

	public ThreadB(ReentrantLockDemo demo) {
		this.demo = demo;
	}

	@Override
	public void run() {
		demo.get();
	}
}
