package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_005;

public class Client {

	public static void main(String[] args) {

		DataObject dataObject = new DataObject();
		String key = "rw_key";

		for(int i = 0; i < 5; i++) {
			new Thread(new ThreadA(dataObject, key), "threadA_" + i).start();
		}

	}
}
