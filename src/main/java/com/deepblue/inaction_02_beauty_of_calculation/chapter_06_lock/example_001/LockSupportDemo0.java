package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_001;

import java.util.concurrent.locks.LockSupport;

/**
 * 每个调用 LockSupport 的线程 都会持有一个许可证, 默认情况下 调用 LockSupport.park() 的线程是不会持有许可证的!
 * 		持有许可证: 调用 LockSupport.park() 则方法立即返回
 * 		不持有许可: 调用 LockSupport.park() 则当前线程阻塞
 */
public class LockSupportDemo0 {

	public static void main(String[] args) {

		System.out.println("park method begin!");

		LockSupport.park();

		System.out.println("park method  end !");

	}
}
