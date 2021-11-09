package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_001;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo3 {

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA begin park!");

				LockSupport.park();

				System.out.println("threadA  end  park!");
			}
		});

		threadA.start();

		Thread.currentThread().sleep(2000L);

		threadA.interrupt();

		System.out.println("thread main invoke end!");

	}
}
