package com.deepblue.inaction_02_beauty_of_calculation.chapter_03_thread_local_random.example_001;

import java.util.Random;

public class RandomDemo {

	public static void main(String[] args) {
		// 创建一个种子生成器
		Random random = new Random();
		// 随机产生10次 0~5(不包含5) 的随机数
		for(int i = 0; i < 10; i++) {
			int value = random.nextInt(5);
			System.out.println(value);
		}
	}
}
