package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_005;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemoOther {

	public static void main(String[] args) {

		SynchronousQueue<String> queue = new SynchronousQueue<String>();
		// 定义一个数量为1的信号量, 信号量相当于互斥锁的作用!
		Semaphore sem = new Semaphore(1);

		for(int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					sem.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				String element = null;
				try {
					element = queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " take element is " + element);

				sem.release();
			}, "consumer_" + i).start();
		}


		for(int i = 0; i < 10; i++) {
			try {
				queue.put("element_" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
