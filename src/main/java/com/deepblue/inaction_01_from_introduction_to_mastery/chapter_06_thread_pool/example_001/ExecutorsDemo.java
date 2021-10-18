package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_06_thread_pool.example_001;

import java.util.concurrent.Executors;


/**
 * 认识 线程池工厂 的三种 构建线程池的 方法
 */
public class ExecutorsDemo {

	public static void main(String[] args) {
		// 创建只包含单线程的线程池
		Executors.newSingleThreadExecutor();
		// 创建包含固定数量的线程池
		Executors.newFixedThreadPool(2);
		// 创建能够缓存的线程池
		Executors.newCachedThreadPool();
	}
}
