package com.deepblue.inaction_02_beauty_of_calculation.chapter_05_copy_on_write.example_001;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO 本例中, 因为 CopyOnWriteArrayList 中的 remove() 和 get() 在不同的线程中 执行, 一旦 array 重新指向了 remove数据后的副本, 这样就会 造成 get() 失败的情况， 从而抛出异常!
 */
public class CopyOnWriteArrayListDemo {

	public static void main(String[] args) {

		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("AAA");
		list.add("BBB");
		list.add("CCC");
		list.forEach(System.out::println);

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				list.remove(2);
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					String result;
					try {
						result = list.get(2);
					} catch (Exception e) {
						e.printStackTrace();
						result = null;

						// TODO 一旦当前元素 不存在 则立即终止 循环获取
						break;
					}

					System.out.println("i = " + i + " , result = " + result);

					try {
						Thread.currentThread().sleep(600L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		threadB.start();
		try {
			Thread.currentThread().sleep(1500L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadA.start();

	}
}
