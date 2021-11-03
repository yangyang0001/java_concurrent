package com.deepblue.inaction_02_beauty_of_calculation.chapter_02_safe.example_006;

/**
 * 因为 @sun.misc.Contended 是作用在 rt 包下的, 我们要用于我们自己的 代码中 TODO 需要开启 VMOption -XX:-RestrictContended 才能使用, 填充默认宽度为 28
 * 查看使用 VMOption参数 -XX:+PrintFlagsFinal
 */
@sun.misc.Contended
public class FieldLong {
	private volatile long value = 0L;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
