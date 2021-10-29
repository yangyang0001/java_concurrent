package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_008;

import java.util.concurrent.CountDownLatch;

public class YieldDemo {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch cdl = new CountDownLatch(1);

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					cdl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("threadA begin run");
				for (int i = 0; i < 5; i++) {
					if(i % 5 == 0) {
						System.out.println("threadA yield running");
						Thread.yield();
					}
				}
				System.out.println("threadA  end  run");
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
				System.out.println("threadB begin run");
				for (int i = 0; i < 5; i++) {
					if(i % 5 == 0) {
						System.out.println("threadB yield running");
						Thread.yield();
					}
				}
				System.out.println("threadB  end  run");
			}
		});

		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					cdl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("threadC begin run");
				for (int i = 0; i < 5; i++) {
					if(i % 5 == 0) {
						System.out.println("threadC yield running");
						Thread.yield();
					}
				}
				System.out.println("threadC  end  run");
			}
		});

		threadA.start();
		threadB.start();
		threadC.start();

		System.out.println("threadA threadB threadC begin running");
		Thread.currentThread().sleep(1000L);
		cdl.countDown();

		System.out.println("thread main invoke end!");
	}


}
