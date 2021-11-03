package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_004;

import java.lang.reflect.Field;

public class ReflectDemo {

	public static void main(String[] args) {

		Field[] fields = Mine.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
		}

	}
}
