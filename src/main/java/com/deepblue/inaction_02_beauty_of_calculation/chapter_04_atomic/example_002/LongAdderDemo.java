package com.deepblue.inaction_02_beauty_of_calculation.chapter_04_atomic.example_002;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

	public static void main(String[] args) {

		LongAdder adder = new LongAdder();

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 5; i++) {
					adder.increment();
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 5; i++) {
					adder.increment();
				}
			}
		});

		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 5; i++) {
					adder.increment();
				}
			}
		});

		threadA.start();
		threadB.start();
		threadC.start();

		try {
			threadA.join();
			threadB.join();
			threadC.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("LongAdder sum = " + adder.longValue());
	}
}
