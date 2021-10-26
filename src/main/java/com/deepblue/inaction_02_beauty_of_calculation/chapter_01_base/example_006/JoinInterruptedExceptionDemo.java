package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_006;

public class JoinInterruptedExceptionDemo {

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA begin run!");
				try {
					Thread.sleep(1000L);

					for(;;) {}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread mainThread = Thread.currentThread();

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				// 子线程 threadB 中断 主线程 mainThread
				try {
					Thread.currentThread().sleep(1000L);

					mainThread.interrupt();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		threadA.start();
		threadB.start();

		try {
			// 等待 threadA 死亡
			threadA.join();
		} catch (InterruptedException e) {
			System.out.println("main thread " + e);
		}


	}
}
