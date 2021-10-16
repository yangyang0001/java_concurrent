package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_008;

import java.util.concurrent.CyclicBarrier;

/**
 * TODO 重点关注其他线程都完成后 才能进行 下一阶段的线程执行! 例如 大数据的汇总工作!
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, new SuffixThread());

		Thread threadA = new Thread(new PrefixThread(barrier));
		Thread threadB = new Thread(new PrefixThread(barrier));
		Thread threadC = new Thread(new PrefixThread(barrier));

		threadA.start();
		threadB.start();
		threadC.start();
	}
}
