package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_006;

/**
 * 因为 array 是 非连续性的一块内存, 在缓存行中不起作用, 所以用时 比 CacheLineDemo 用时长!
 */
public class NoCacheLineDemo {

	public static final int LINE_NUM = 1024;
	public static final int COLU_NUM = 1024;

	public static void main(String[] args) {

		long[][] array = new long[LINE_NUM][COLU_NUM];

		long start = System.currentTimeMillis();
		for(int i = 0; i < LINE_NUM; i++) {
			for (int j = 0; j < COLU_NUM; j++) {
				array[j][i] = i * 2 + j;
			}
		}
		long end = System.currentTimeMillis();

		System.out.println("no cache line cost time = " + (end - start));
	}
}
