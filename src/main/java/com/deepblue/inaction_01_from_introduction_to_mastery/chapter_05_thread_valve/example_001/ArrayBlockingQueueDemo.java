package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_001;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueDemo {

	public static void main(String[] args) {

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);

		// 4个消费线程
		for(int i = 0; i < 4; i++) {
			new Thread(() -> {
				while(true) {
					try {
						String log = queue.take();
						parseLog(log);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "consumer_" + i).start();
		}

		// 4个生产者线程, 每个线程生产4个元素
		for(int i = 0; i < 4; i++) {
			new Thread(() -> {
				for(int j = 0; j < 4; j++) {
					try {
						queue.put("element_" + Thread.currentThread().getName() + "_" + j);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "producer_" + i).start();
		}
	}

	public static void parseLog(String log) {
		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " parseLog invoke, the log is " + log);
	}
}
