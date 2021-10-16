package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_04_collection.example_004;

import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetDemo {

	public static void main(String[] args) {
		CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
		set.add("one");
		set.add("one");
		set.add("two");

		set.stream().forEach(System.out::println);
	}
}
