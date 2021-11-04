package com.deepblue.inaction_02_beauty_of_calculation.chapter_03_thread_local_random.example_002;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 避免了 Random 的自旋 CAS 操作, 这样可以节省更多的 CPU 资源!
 */
public class ThreadLocalRandomDemo {

	public static void main(String[] args) {

		ThreadLocalRandom random = ThreadLocalRandom.current();
		for(int i = 0; i < 10; i++) {
			int value = random.nextInt(5);
			System.out.println(value);
		}

	}

}
