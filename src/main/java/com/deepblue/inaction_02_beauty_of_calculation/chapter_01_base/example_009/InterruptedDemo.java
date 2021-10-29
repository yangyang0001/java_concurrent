package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_009;

public class InterruptedDemo {

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				// 如果线程被中断, 则线程退出循环
				while (!Thread.currentThread().isInterrupted()) {
					System.out.println("threadA interrupted flag is " + Thread.currentThread().isInterrupted());
				}

				System.out.println("threadA interrutped flag is " + Thread.currentThread().isInterrupted() + " and run method invoke end");
			}
		});

		threadA.start();
		Thread.currentThread().sleep(2000L);
		threadA.interrupt();

		System.out.println("thread main invoked end");
	}
}
