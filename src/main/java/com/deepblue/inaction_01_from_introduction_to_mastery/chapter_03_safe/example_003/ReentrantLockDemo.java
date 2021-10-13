package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_003;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	private static ReentrantLock lock = new ReentrantLock();

	public void set() {
		try {
			// 加锁
			lock.lock();

			System.out.println(Thread.currentThread().getName() + " set method invoke begin");
			Thread.currentThread().sleep(1000L);
			System.out.println(Thread.currentThread().getName() + " set method invoke end");

			// 解锁
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void get() {
		try {
			// 加锁
			lock.lock();

			System.out.println(Thread.currentThread().getName() + " get method invoke begin");
			Thread.currentThread().sleep(1000L);
			System.out.println(Thread.currentThread().getName() + " get method invoke end");

			// 解锁
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
