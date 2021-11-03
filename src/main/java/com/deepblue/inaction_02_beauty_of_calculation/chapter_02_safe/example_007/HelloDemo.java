package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_007;

public class HelloDemo {

	public synchronized void helloA() {
		System.out.println("helloA");
	};

	public synchronized void helloB() {
		System.out.println("helloB");
		helloA();
	}
}
