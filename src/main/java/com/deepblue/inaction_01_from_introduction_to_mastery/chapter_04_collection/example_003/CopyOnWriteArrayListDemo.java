package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_04_collection.example_003;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

	public static void main(String[] args) {

		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");

		list.stream().forEach(System.out::println);
	}
}
