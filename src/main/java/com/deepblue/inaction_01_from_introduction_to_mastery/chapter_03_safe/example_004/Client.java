package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_004;

public class Client {

	public static void main(String[] args) {

		Count count = new Count();
		for(int i = 0; i < 2; i++) {
			new Thread(new ThreadA(count), "GetThreadA_" + i).start();
		}

		for(int i = 0; i < 2; i++) {
			new Thread(new ThreadB(count), "SetThreadB_" + i).start();
		}

	}
}
