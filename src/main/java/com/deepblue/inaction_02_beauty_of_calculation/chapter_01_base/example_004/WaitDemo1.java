package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_004;

/**
 * 使用 resource.wait(long timeout) 等待指定时间后, 直接进入运行状态
 */
public class WaitDemo1 {

	private static final Object resoure = new Object();

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resoure) {
					System.out.println("threadA begin wait");
					try {
						resoure.wait(2000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("threadA  end  wait");
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resoure) {
					System.out.println("threadB begin wait");
					try {
						resoure.wait(3000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("threadB  end  wait");
				}
			}
		});

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("thread main method invoke end");
	}
}
