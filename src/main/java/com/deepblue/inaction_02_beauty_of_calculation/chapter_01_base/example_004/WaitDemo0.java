package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_004;

public class WaitDemo0 {

	private static volatile Object resourceA = new Object();
	private static volatile Object resourceB = new Object();

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (resourceA) {
						System.out.println("threadA get resourceA lock");

						synchronized (resourceB) {
							System.out.println("threadA get resourceB lock");

							System.out.println("threadA release resourceA lock");
							resourceA.wait();
						}
					}
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(1000L);

					synchronized (resourceA) {
						System.out.println("threadB get resourceA lock");

						System.out.println("threadB try get resourceB lock ...");
						synchronized (resourceB) {
							System.out.println("threadB get resourceB lock");

							System.out.println("threadB release resourceA lock");
							resourceA.wait();
						}
					}

				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
			}
		});

		// 启动线程
		threadA.start();
		threadB.start();

		// 等待线程死亡
		threadA.join();
		threadB.join();

		System.out.println("thread main invoke end");



	}
}
