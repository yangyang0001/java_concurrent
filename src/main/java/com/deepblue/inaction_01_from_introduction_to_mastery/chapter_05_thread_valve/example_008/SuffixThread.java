package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_008;

public class SuffixThread implements Runnable{
	@Override
	public void run() {
		System.out.println("SuffixThread running start");
		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("SuffixThread running end");
	}
}
