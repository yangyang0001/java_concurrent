package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_001;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo4 {

	private static Object o = new Object();

	public static void main(String[] args) {

		System.out.println("begin park nanos!");

		LockSupport.unpark(Thread.currentThread());

		LockSupport.parkNanos(1000000000L);

		System.out.println("end   park nanos!");

	}

}
