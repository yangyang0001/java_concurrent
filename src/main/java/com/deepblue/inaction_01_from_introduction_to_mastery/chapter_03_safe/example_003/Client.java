package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_003;

public class Client {

	public static void main(String[] args) {
		ReentrantLockDemo demo = new ReentrantLockDemo();

		for(int i = 0; i < 2; i++) {
			new Thread(new ThreadA(demo),"SetThreadA_" + i).start();
		}

		for(int i = 0; i < 2; i++) {
			new Thread(new ThreadB(demo),"GetThreadB_" + i).start();
		}
	}
}
