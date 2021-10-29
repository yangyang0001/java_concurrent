package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_006;

import java.util.concurrent.CountDownLatch;

/**
 * TODO 注意: 书本中给出的例子 错误
 * @see com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_008.YieldDemo
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
