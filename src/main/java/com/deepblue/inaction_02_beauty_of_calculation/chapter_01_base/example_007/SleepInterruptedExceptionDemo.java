package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_007;

public class SleepInterruptedExceptionDemo {

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA begin run");

				try {
					Thread.currentThread().sleep(10000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("threadA  end  run ");
			}
		});

		// 子线程 启动
		threadA.start();

		// 主线程
		Thread thread = Thread.currentThread();

		// 主线程 中断 子线程
		threadA.interrupt();

	}
}
