package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_04_collection.example_002;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ConcurrentHashMapDemo {

	public static void main(String[] args) {

		ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
		chm.put("one", 1);
		chm.put("two", 2);
		chm.put("three", 3);

		System.out.println(Thread.currentThread().getName() + " two = " + chm.get("two"));
		if(chm.containsKey("one") && chm.get("one").equals(1)) {
			chm.remove("one");
		}

		for(Map.Entry<String, Integer> entry : chm.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

	}
}
