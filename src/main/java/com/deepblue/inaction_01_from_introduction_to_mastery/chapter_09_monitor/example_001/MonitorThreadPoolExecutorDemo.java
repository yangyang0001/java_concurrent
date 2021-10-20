package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_09_monitor.example_001;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MonitorThreadPoolExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executor = new MonitorThreadPoolExecutor(5, 5, 0,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

		for(int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.currentThread().sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			executor.execute(runnable);
		}

		executor.shutdown();
		System.out.println("thread main invoke end!");
	}
}
