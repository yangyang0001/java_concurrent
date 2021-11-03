package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_004;

import com.alibaba.fastjson.JSON;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafe {

	static Unsafe unsafe = null;

	static long stateOffset = 0;

	// unsafe.objectFieldOffset(state); 静态变量会抛出异常! 只能针对 非静态字段
	private volatile long state = 0;

	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
			Field state = TestUnsafe.class.getDeclaredField("state");
			System.out.println(JSON.toJSONString(state));
			stateOffset = unsafe.objectFieldOffset(state);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestUnsafe test = new TestUnsafe();
		boolean flag = unsafe.compareAndSwapLong(test, stateOffset, 0, 1);
		System.out.println("flag is " + flag);
	}
}
