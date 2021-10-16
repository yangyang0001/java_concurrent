package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_004;

import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class DelayQueueDemo {

	public static void main(String[] args) {

		DelayQueue<DelayExample> delayExamples = new DelayQueue<DelayExample>();

		for(int i = 0; i < 16; i++) {
			delayExamples.put(new DelayExample(Long.valueOf(i), "name_" + i, new Date().getTime(), 5));
		}

		for(int i = 0; i < 4; i++) {
			new Thread(() -> {
				while(true) {
					// 获取元素 但 不移除此队列的头部, 如果此队列为空则返回 null
//					Student student = students.peek();
					// 获取元素 并移除此队列的头部
					try {
						Thread.currentThread().sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					DelayExample delayExample = delayExamples.poll();
					System.out.println(Thread.currentThread().getName() + " example is " + JSON.toJSONString(delayExample));
				}
			}, "consumer_" + i).start();
		}

	}
}
