package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_04_collection.example_001;

import java.util.Hashtable;

public class HashtableDemo {

	public static void main(String[] args) {

		Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
		hashtable.put("one",   1);
		hashtable.put("two",   2);
		hashtable.put("three", 3);

		Integer two = hashtable.get("two");
		if(two != null) {
			System.out.println(Thread.currentThread().getName() + " two = " + two);
		}

	}
}
