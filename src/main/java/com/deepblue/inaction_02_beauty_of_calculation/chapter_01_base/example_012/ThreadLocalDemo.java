package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_012;

import com.alibaba.fastjson.JSON;

public class ThreadLocalDemo {

	public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new ThreadA(threadLocal), "threadA");
		Thread threadB = new Thread(new ThreadA(threadLocal), "threadB");

		threadA.start();
		threadB.start();

		threadA.join();
		threadB.join();

		System.out.println(JSON.toJSONString(threadLocal));

		System.out.println("thread main method invoke end");
	}

}
