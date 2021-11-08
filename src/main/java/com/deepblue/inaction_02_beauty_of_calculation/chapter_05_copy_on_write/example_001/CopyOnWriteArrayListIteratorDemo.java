package com.deepblue.inaction_02_beauty_of_calculation.chapter_05_copy_on_write.example_001;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListIteratorDemo {

	private static volatile CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

	public static void main(String[] args) {

		list.add("AAA");
		list.add("BBB");
		list.add("CCC");
		list.add("DDD");
		list.add("EEE");
		list.add("FFF");

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				list.set(0, "zhangsan");
				list.remove(2);
				list.remove(3);
			}
		});

		Iterator<String> iterator = list.iterator();

		threadA.start();
		try {
			threadA.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		list.stream().forEach(System.out::println);

		System.out.println("-------------------------------------------------------------------");

		while(iterator.hasNext()) {
			String next = iterator.next();
			System.out.println("iterator next = " + next);
		}

	}
}
