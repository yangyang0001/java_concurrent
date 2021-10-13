package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_007;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {

	public static void main(String[] args) {
		AtomicInteger ai = new AtomicInteger(0);
		System.out.println(ai.get());					// 0
		System.out.println(ai.getAndSet(5));	// 0
		System.out.println(ai.getAndIncrement());		// 5
		System.out.println(ai.getAndDecrement());		// 6
		System.out.println(ai.getAndAdd(10));	// 5
		System.out.println(ai.get());					// 15
	}
}
