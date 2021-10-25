package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_006;

public class NotifyDemo {

	private static final Object resourceA = new Object();

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resourceA) {
					System.out.println("threadA get resourceA lock");
					try {
						System.out.println("threadA begin wait");
						resourceA.wait();
						System.out.println("threadA sleep 5s");
						Thread.currentThread().sleep(5000L);
						System.out.println("threadA  end  wait");
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resourceA) {
					System.out.println("threadB get resourceA lock");
					try {
						System.out.println("threadB begin wait");
						resourceA.wait();
						System.out.println("threadB sleep 10s");
						Thread.currentThread().sleep(10000L);
						System.out.println("threadB  end  wait");
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}
				}
			}
		});

		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resourceA) {
					System.out.println("threadC get resourceA lock and begin notify");
					resourceA.notifyAll();
				}
			}
		});

		threadA.start();
		threadB.start();

		// 主线程 休息1秒 保证 threadA, threadB 都被 添加到 resoureA 中的阻塞队列中, 避免在 notifyAll() 后 threadA 或 threadB 才执行 wait() 方法
		Thread.currentThread().sleep(1000L);

		threadC.start();

		// 等待线程死亡
		threadA.join();
		threadB.join();
		threadC.join();

		System.out.println("thread main invoke end");
	}
}
