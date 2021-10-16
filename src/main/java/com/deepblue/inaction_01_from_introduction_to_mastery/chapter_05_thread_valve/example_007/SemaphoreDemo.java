package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_007;

import java.util.concurrent.Semaphore;

/**
 * TODO 最大并发数量 信号量  举例 服务器端只允许有3个线程同时访问, 这时候来了10个线程访问
 */
public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < 10; i++) {
			Thread threadA = new Thread(new ThreadA(semaphore), "thread_" + i);
			threadA.start();
		}
	}
}
