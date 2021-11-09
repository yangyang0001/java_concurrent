package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_001;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo5 {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("begin park!");

				LockSupportDemo5 demo5 = new LockSupportDemo5();
				demo5.testPark();

				System.out.println("end   park!");
			}
		});

		threadA.start();

		Field field = Thread.class.getDeclaredField("parkBlocker");
		field.setAccessible(true);

		Object blocker = field.get(threadA);
		System.out.println("blocker :" + JSON.toJSONString(blocker));
	}

	public void testPark() {
		// 使用 jstack -pid 能轻松定位是 哪个类 挂起了
		LockSupport.park(this);
	}
}
