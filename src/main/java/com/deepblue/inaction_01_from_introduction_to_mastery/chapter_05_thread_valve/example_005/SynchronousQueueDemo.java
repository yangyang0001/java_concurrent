package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_005;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

	public static void main(String[] args) {

		SynchronousQueue<String> queue = new SynchronousQueue<String>();

		for(int i = 0; i < 4; i++) {
			new Thread(() -> {
				for(int j = 0; j < 4; j++) {
					String element = null;
					try {
						element = queue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " take element is " + element);
				}
			}, "consumer_" + i).start();
		}

		for(int i = 0; i < 16; i++) {
			try {
				queue.put("element_" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
