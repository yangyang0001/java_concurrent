package com.deepblue.inaction_02_beauty_of_calculation.chapter_04_atomic.example_001;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicDemo {

	private static AtomicLong atomicLong = new AtomicLong();

	private static Integer[] arrayOne = new Integer[]{0,  1, 2, 3, 0, 5, 6, 0, 56, 0};
	private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < arrayOne.length; i++) {
					if(arrayOne[i].intValue() == 0) {
						atomicLong.incrementAndGet();
					}
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < arrayTwo.length; i++) {
					if(arrayTwo[i].intValue() == 0) {
						atomicLong.incrementAndGet();
					}
				}
			}
		});

		threadA.start();
		threadB.start();

		// 线程结束
		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("all zero count = " + atomicLong.get());

	}
}
