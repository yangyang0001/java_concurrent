package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_003;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Client {

	public static void main(String[] args) {
		Thread main = Thread.currentThread();
		System.out.println(main.getName() + " main method invoke start .......");

		FutureTask<String> future = new FutureTask<String>(new ThreadA());
		Thread thread = new Thread(future, "future task");
		thread.start();

		try {
			String result = future.get();
			System.out.println("result is " + result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println(main.getName() + " main method invoke end   .......");
	}
}
