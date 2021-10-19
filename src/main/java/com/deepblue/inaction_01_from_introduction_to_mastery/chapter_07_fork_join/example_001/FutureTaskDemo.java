package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_001;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

	public static void main(String[] args) {

		FutureTask<String> taskA = new FutureTask<String>(new SonTaskA());
		Thread threadA = new Thread(taskA, "SonTaskA");
		threadA.start();

		try {
			String resultA = taskA.get();
			System.out.println("SonTaskA get result is " + resultA);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		FutureTask<String> taskB = new FutureTask<String>(new SonTaskB());
		Thread threadB = new Thread(taskB, "SonTaskB");
		threadB.start();

		try {
			String resultB = taskB.get();
			System.out.println("SonTaskB get result is " + resultB);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
}
