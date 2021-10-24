package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_001;

public class ThreadA extends Thread {

	@Override
	public void run() {
		System.out.println(this.getName() + " running invoked with thread");
	}

}
