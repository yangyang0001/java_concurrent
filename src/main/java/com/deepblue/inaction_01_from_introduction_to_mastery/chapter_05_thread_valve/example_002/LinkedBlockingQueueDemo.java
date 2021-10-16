package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_002;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

	public static void main(String[] args) {

		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(16);

		for (int i = 0; i < 4; i++) {
			new Thread(() -> {
				while (true) {
					try {
						Thread.currentThread().sleep(1000L);
						String result = queue.take();
						System.out.println(Thread.currentThread().getName() + " result is " + result);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "consumer_" + i).start();
		}

		for (int i = 0; i < 4; i++) {
			new Thread(() -> {
				for (int j = 0; j < 4; j++) {
					try {
						queue.put("element_" + Thread.currentThread().getName() + "_" + j);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "producer_" + i).start();
		}



	}
}
