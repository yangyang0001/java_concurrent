package com.deepblue.inaction_02_beauty_of_calculation.chapter_04_atomic.example_002;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * 函数版的 LongAdder 这里能够设置 初始值 identity = 5; 即计算 applyAsLong(left, right) 中的 left 值为5
 */
public class LongAccumlatorDemo {

	public static void main(String[] args) {

		LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
			@Override
			public long applyAsLong(long left, long right) {
				System.out.println("left  = " + left);
				System.out.println("right = " + right);
				return left + right;
			}
		}, 5);

		accumulator.accumulate(10);

		System.out.println(accumulator.longValue());
	}
}
