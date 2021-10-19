package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_002;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinDemo {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool();

		CountTask taskA = new CountTask(1, 1);
		ForkJoinTask<Integer> submitA = pool.submit(taskA);

		try {
			Integer resultA = submitA.get();
			System.out.println("taskA result is " + resultA);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		CountTask taskB = new CountTask(1, 100);
		ForkJoinTask<Integer> submitB = pool.submit(taskB);
		try {
			Integer resultB = submitB.get();
			System.out.println("taskB result is " + resultB);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


		/**
		 * TODO 可能会用到的抛出异常的处理
		 * ForkJoinTask task = null;
		 * if(task.isCompletedAbnormally()) {
		 * 		Throwable exception = task.getException();
		 * 		System.out.println("exception :" + exception);
		 * }
		 */
	}
}
