package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_012;

public class ThreadLocalDemo {

	public static void main(String[] args) {

		ThreadLocal<String> threadLocal = new ThreadLocal<>();
		Thread threadA = new Thread(new ThreadA(threadLocal), "threadA");
		Thread threadB = new Thread(new ThreadA(threadLocal), "threadB");

		threadA.start();
		threadB.start();

		System.out.println("thread main method invoke end");
	}

}
