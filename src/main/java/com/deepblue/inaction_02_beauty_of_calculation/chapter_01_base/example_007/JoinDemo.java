package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_007;


public class JoinDemo {

	public static void main(String[] args) {

	    Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA running start");
				try {
					Thread.currentThread().sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("threadA running end");

			}
		});

	    Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadB running start");
				try {
					Thread.currentThread().sleep(2000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("threadB running end");

			}
		});

	    Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadC running start");
				try {
					Thread.currentThread().sleep(5000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("threadC running end");

			}
		});

	    // 线程开始
		threadA.start();
		threadB.start();
		threadC.start();

		// 等待线程执行完毕
		try {
			threadA.join();
			threadB.join();
			threadC.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("thread main invoked end");
	}
}
