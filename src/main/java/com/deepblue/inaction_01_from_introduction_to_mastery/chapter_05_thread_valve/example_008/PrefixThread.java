package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_008;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PrefixThread implements Runnable{

	private CyclicBarrier barrier;

	public PrefixThread(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName() + " running start");
		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " running end");

		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
