package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_012;

public class ThreadA implements Runnable {

	private ThreadLocal<String> threadLocal;

	public ThreadA() {}

	public ThreadA(ThreadLocal<String> threadLocal) {
		this.threadLocal = threadLocal;
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " run method begin");
		setValue(thread.getName());

		try {
			thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(thread.getName() + " run method middle value = :" + getValue());

		// 清除当前线程的 threadLocalMap 中的 value 值
		threadLocal.remove();
		System.out.println(thread.getName() + " run method remove after value = :" + getValue());

	}

	public void setValue(String value) {
		threadLocal.set(value);
	}

	public String getValue() {
		return threadLocal.get();
	}
}
