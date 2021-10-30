package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_010;

import java.util.concurrent.CountDownLatch;

public class DeadLockDemo {

	private static final Object objA = new Object();
	private static final Object objB = new Object();

	public static void main(String[] args) {

		CountDownLatch cdl = new CountDownLatch(1);

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					cdl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (objA) {
					System.out.println("threadA run begin acquire objA");
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (objB) {
						System.out.println("threadA acquire objB begin");
						System.out.println("threadA release objB end ");
					}
					System.out.println("threadA run  end  release objA");
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					cdl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (objB) {
					System.out.println("threadB run begin acqurie objB");
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (objA) {
						System.out.println("threadB acqurie objA begin");
						System.out.println("threadB release objA  end!");

					}
					System.out.println("threadB run  end  release objB");
				}
			}
		});

		threadA.start();
		threadB.start();
		cdl.countDown();

		System.out.println("thread main method invoke end");
	}

}
