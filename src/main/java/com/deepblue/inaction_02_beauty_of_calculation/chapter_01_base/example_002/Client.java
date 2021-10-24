package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_002;

public class Client {

	public static void main(String[] args) {
		Thread thread = new Thread(new ThreadA(), "runnable thread");
		thread.start();
	}

}
