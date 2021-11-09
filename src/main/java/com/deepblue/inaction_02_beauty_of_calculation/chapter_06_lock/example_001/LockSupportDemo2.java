package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_001;

import java.util.concurrent.locks.LockSupport;

/**
 * threadA 中执行 LockSupport.park();  则 threadA 直接挂起!
 * 其他线程中 执行 LockSupport.unpark(threadA); 则 threadA 获取到许可证, 接着运行!
 */
public class LockSupportDemo2 {

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA begin park!");
				LockSupport.park();
				System.out.println("threadA  end  park!");
			}
		});

		threadA.start();

		Thread.currentThread().sleep(2000L);

		LockSupport.unpark(threadA);

		System.out.println("thread main invoke end!");
	}
}
