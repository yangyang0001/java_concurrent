package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_009;

/**
 * TODO 这个例子非常重要
 */
public class InterruptedBookDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = Thread.currentThread();

		Thread threadOne = new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {}
			}
		});

		// 启动线程
		threadOne.start();

		// 设置中断标识
		threadOne.interrupt();			// 设置 threadOne 的 中断值为 true

		// 查看 threadOne 的中断标识
		System.out.println("1_threadOne interrupt flag is " + threadOne.isInterrupted());	// 获取 threadOne 的中断值为 true

		// 错误理解: 重置 threadOne 的中断标识  TODO 正确理解 为 main thread
		System.out.println("2_threadOne interrupt flag is " + threadOne.interrupted());		// 获取 main thread 的中断值为 false, 并 取消 main thread 的中断, 重置 main thread 的中断值为 false

		// 重置 main thread 的中断标识
		System.out.println("3_threadOne interrupt flag is " + Thread.interrupted());		// 获取 main thread 的中断值为 false, 并 取消 main thread 的中断, 重置 main thread 的中断值为 false

		// 查看 threadOne 的中断标识
		System.out.println("4_threadOne interrupt flag is " + threadOne.isInterrupted());  	// 获取 threadOne 的中断值为 true

		threadOne.join();

		System.out.println("thread main method invoke end");
	}
}
