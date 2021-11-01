package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_013;

/**
 * Thread
 */
public class InheritableThreadLocalDemo {

	public static void main(String[] args) {
		ThreadLocal<String> threadLocal = new ThreadLocal<>();				// TODO 子线程 不可以获取 父类线程中的变量
//		ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();	// TODO 子线程 可以获取到 父线程中的变量

		// 父类线程 设置值
		threadLocal.set("main thread");

		System.out.println("thread main before get value = :" + threadLocal.get());

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadA run invoke get value = :" + threadLocal.get());
			}
		});

		threadA.start();

		System.out.println("thread main  after get value = :" + threadLocal.get());
	}

}
