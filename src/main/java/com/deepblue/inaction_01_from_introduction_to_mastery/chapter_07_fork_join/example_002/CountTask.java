package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join.example_002;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

	/**
	 * 每个任务最多包含计算量
	 */
	private static final Integer splitSize = 3;

	/**
	 * 开始数量
	 */
	private Integer start;

	/**
	 * 结束数量
	 */
	private Integer end;

	public CountTask(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if((end - start) <= splitSize) {
			for(int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int middle = (start + end) / 2;
			CountTask firstTask = new CountTask(start, middle);
			CountTask secndTask = new CountTask(middle + 1, end);

			/**
			 * 继续拆分
			 */
			firstTask.fork();
			secndTask.fork();

			/**
			 * 获取结果
			 */
			Integer firstResult = firstTask.join();
			Integer secndResult = secndTask.join();

			sum = firstResult + secndResult;
		}

		return sum;
	}
}
