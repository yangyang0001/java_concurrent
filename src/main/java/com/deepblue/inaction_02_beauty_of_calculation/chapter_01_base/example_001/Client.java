package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_001;

public class Client {

	public static void main(String[] args) {
		ThreadA thread = new ThreadA();
		thread.setName("my thread");
		thread.start();
	}

}
