package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve.example_004;

import lombok.*;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DelayExample implements Delayed {

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 创建时间 毫秒
	 */
	private long create;

	/**
	 * 延迟时间 单位秒
	 */
	private long duration;

	/**
	 * 返回值解释:
	 * 		大于0	 : 表示还处于延迟阶段, 获取不到改元素
	 * 		小于等于0 : 表示已经过了延迟阶段, 如果 Queue 中有该元素则可以获取到
	 * @param unit
	 * @return
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		/**
		 * 此处代码分析:
		 * 		开始时刻(毫秒) + 延迟时长(秒) = 到期时刻
		 * 	    到期时刻(毫秒) - 当前时刻(时刻) = 剩余时长
		 * 	    剩余时长 大于0	 : 表示还处于延迟阶段, 获取不到改元素
		 * 	 	剩余时长 小于等于0 : 表示已经过了延迟阶段, 如果 Queue 中有该元素则可以获取到
		 */
		return unit.convert(DateUtils.addSeconds(new Date(this.getCreate()), Long.valueOf(this.getDuration()).intValue()).getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		DelayExample target = (DelayExample) o;
		return this.getCreate() > target.getCreate() ? 1 : (this.getCreate() < target.getCreate() ? -1 : 0);
	}
}
