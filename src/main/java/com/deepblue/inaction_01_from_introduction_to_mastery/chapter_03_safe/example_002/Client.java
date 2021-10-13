package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_002;


public class Client {

	public static void main(String[] args) {
		Count count = new Count();
		Thread threadA = new Thread(new ThreadA(count), "threadA");
		Thread threadB = new Thread(new ThreadA(count), "threadB");
		Thread threadC = new Thread(new ThreadA(count), "threadC");
		Thread threadD = new Thread(new ThreadA(count), "threadD");
		Thread threadE = new Thread(new ThreadA(count), "threadE");

		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		threadE.start();
	}
}
