package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_003;

/**
 * 非 线程安全的类
 */
public class Count {

	private long count;

	public void increment() {
		count++;
	}

	public long getCount() {
		return count;
	}
}
