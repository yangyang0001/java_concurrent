package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_006;

/**
 *  Cache 伪共享
 *  Cache Line 是 CPU缓存 和 主内存进行交互的 基本单位!
 *  缓存行的使用 Demo
 */
public class CacheLineDemo {

	public static final int LINE_NUM = 1024;
	public static final int COLU_NUM = 1024;

	public static void main(String[] args) {

		long[][] array = new long[LINE_NUM][COLU_NUM];

		long start = System.currentTimeMillis();
		for(int i = 0; i < LINE_NUM; i++) {
			for(int j = 0; j < COLU_NUM; j++) {
				array[i][j] = i * 2 + j;
			}
		}
		long end = System.currentTimeMillis();

		System.out.println("cache line cost time = " + (end - start));
	}

}
