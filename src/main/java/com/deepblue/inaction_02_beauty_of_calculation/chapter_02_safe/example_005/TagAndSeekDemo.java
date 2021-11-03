package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_005;

/**
 * 	指令重排序 问题
 * 	volatile 关键字修饰的变量就可以 防止一部分指令重排!
 * 		volatile 写入时, 可确保 volatile 变量写入之前的操作 不会 指令重排在 volatile 写入操作之后
 * 		volatile 读出时, 可确保 volatile 变量读出之后的操作 不会 指令重排在 volatile 读出操作之前
 */
public class TagAndSeekDemo {

	public static int num = 0;
	public static volatile boolean ready = false;

	public static void main(String[] args) {

		Thread readThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()) {
					System.out.println("read thread before into if ready = " + ready);
					if(ready) {
						System.out.println("read thread num + num = "  + (num + num));
					}
				}
				System.out.println("read thread over");
			}
		});
		readThread.start();

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread writeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				num = 2;
				ready = true;
				System.out.println("write thread over");
			}
		});
		writeThread.start();

		readThread.interrupt();

		System.out.println("thread main method invoke end");
	}



}
