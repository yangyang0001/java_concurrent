package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_04_collection.example_005;

import java.util.Vector;

public class VectorDemo {

	public static void main(String[] args) {
		Vector<String> list = new Vector<>();
		list.addElement("zhangsan");
		list.addElement("lisi");
		list.addElement("wangwu");

		String element = list.elementAt(1);
		String string  = list.get(1);
		System.out.println("element = " + element + ", string = " + string);

		list.stream().forEach(System.out::println);
	}
}
