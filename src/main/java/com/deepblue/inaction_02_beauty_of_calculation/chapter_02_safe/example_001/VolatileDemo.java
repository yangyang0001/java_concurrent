package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_001;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {

	// 只能保证 主内存的 可见性
	public static volatile int count = 0;

	public static CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) {

		Thread threadA = new Thread(new ThreadA(count, cdl), "threadA");
		Thread threadB = new Thread(new ThreadA(count, cdl), "threadB");
		Thread threadC = new Thread(new ThreadA(count, cdl), "threadC");
		Thread threadD = new Thread(new ThreadA(count, cdl), "threadD");

		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		cdl.countDown();

		System.out.println("thread main method run end!");
	}
}
