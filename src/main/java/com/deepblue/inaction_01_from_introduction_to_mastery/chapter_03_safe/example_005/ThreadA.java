package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_005;

public class ThreadA implements Runnable{

	private DataObject dataObject;

	private String key;

	public ThreadA(DataObject dataObject, String key) {
		this.dataObject = dataObject;
		this.key = key;
	}


	@Override
	public void run() {
		Object result = dataObject.readWrite(key);
		System.out.println(Thread.currentThread().getName() + " result = " +  result);
	}
}
