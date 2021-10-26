package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_004;

public class WaitInterruptedExceptionDemo {

	private static final Object obj = new Object();

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("threadA running start");
					synchronized (obj) {
						obj.wait();
					}
					System.out.println("threadA running end");
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
			}
		});

		threadA.start();

		Thread.sleep(1000L);
		// 主动调用 threadA 中断方法
		threadA.interrupt();

		System.out.println("thread main invoke end");

	}
}
