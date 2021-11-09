package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_001;

import java.util.concurrent.locks.LockSupport;

/**
 * 经测试
 * LockSupport.unpark(threadA); 是让 threadA 获取到许可证!
 * 接着在 threadA 中调用 LockSupport.park() 方法 则会直接运行, 因 threadA 已经获取 到了 许可证了!
 */
public class LockSupportDemo1 {

	public static void main(String[] args) {

		System.out.println("begin park!");

		// 当前线程 获取 许可证
		LockSupport.unpark(Thread.currentThread());

		// 当前线程调用 park() 方法
		LockSupport.park();

		System.out.println("end   park!");


	}
}
