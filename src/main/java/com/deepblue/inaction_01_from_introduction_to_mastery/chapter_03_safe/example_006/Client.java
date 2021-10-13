package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_006;

public class Client {

	public static void main(String[] args) {

		Count count = new Count();
		Thread threadA = new Thread(new ThreadA(count), "threadA");
		Thread threadB = new Thread(new ThreadB(count), "threadB");

		threadA.start();
		threadB.start();

	}
}
