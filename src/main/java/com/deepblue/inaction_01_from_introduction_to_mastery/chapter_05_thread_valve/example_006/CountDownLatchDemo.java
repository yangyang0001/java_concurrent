package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_006;

import java.util.concurrent.CountDownLatch;

/**
 * TODO 使用方式: countDown() 方法放在 run() 方法的最后调用! 一个信号量 有 N 个指示灯, 只有同时亮的时候 才能 执行
 */
public class CountDownLatchDemo {

	public static Integer count;

	public static void main(String[] args) {

		CountDownLatch cdl = new CountDownLatch(3);

		Thread threadA = new Thread(() -> {
			System.out.println("threadA running start ....");
			try {
				Thread.currentThread().sleep(1000L);
				System.out.println("threadA cdl count is " + cdl.getCount());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("threadA running end ....");

			cdl.countDown();
		});

		Thread threadB = new Thread(() -> {
			System.out.println("threadB running start ....");
			try {
				Thread.currentThread().sleep(1000L);
				System.out.println("threadB cdl count is " + cdl.getCount());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("threadB running end ....");

			cdl.countDown();
		});

		Thread threadC = new Thread(() -> {
			System.out.println("threadC running start ....");
			try {
				Thread.currentThread().sleep(1000L);
				System.out.println("threadC cdl count is " + cdl.getCount());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("threadC running end ....");

			cdl.countDown();
		});

		threadA.start();
		threadB.start();
		threadC.start();
	}
}
