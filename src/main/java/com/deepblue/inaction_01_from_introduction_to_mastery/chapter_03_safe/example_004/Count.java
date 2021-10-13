package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_004;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Count {

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public void set() {
		// 加锁
		rwl.writeLock().lock();

		try {
			System.out.println(Thread.currentThread().getName() + " set method invoke begin ...");
			Thread.currentThread().sleep(1000L);
			System.out.println(Thread.currentThread().getName() + " set method invoke end   ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}
	}

	public void get() {
		// 加上读锁
		rwl.readLock().lock();

		try {
			System.out.println(Thread.currentThread().getName() + " get method invoke begin ...");
			Thread.currentThread().sleep(1000L);
			System.out.println(Thread.currentThread().getName() + " get method invoke end   ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
	}
}
