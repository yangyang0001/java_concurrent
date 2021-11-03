package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_002;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

	private static AtomicInteger count = new AtomicInteger(0);

	private static CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) {

		Thread threadA = new Thread(new ThreadA(count, cdl), "threadA");
		Thread threadB = new Thread(new ThreadA(count, cdl), "threadB");
		Thread threadC = new Thread(new ThreadA(count, cdl), "threadC");
		Thread threadD = new Thread(new ThreadA(count, cdl), "threadD");
		Thread threadE = new Thread(new ThreadA(count, cdl), "threadE");
		Thread threadF = new Thread(new ThreadA(count, cdl), "threadF");
		Thread threadG = new Thread(new ThreadA(count, cdl), "threadG");
		Thread threadH = new Thread(new ThreadA(count, cdl), "threadH");
		Thread threadI = new Thread(new ThreadA(count, cdl), "threadI");

		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		threadE.start();
		threadF.start();
		threadG.start();
		threadH.start();
		threadI.start();
		cdl.countDown();

		System.out.println("thread main method invoke end!");
	}
}
