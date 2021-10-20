package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_09_monitor.example_001;

import java.util.concurrent.*;

public class MonitorThreadPoolExecutor extends ThreadPoolExecutor {

	public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}

	public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}


	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println(t.getName() + " beforeExecute invoked");
		super.beforeExecute(t, r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		System.out.println("afterExecute invoked" + r);
	}

	@Override
	protected void terminated() {
		System.out.println(
				"terminated method invoke, corePoolSize = " + this.getCorePoolSize() +	// corePoolSize 大小
				", poolSize = " + this.getPoolSize() +									// 当前线程池中的线程数量
				", largestPoolSize = " + this.getLargestPoolSize() +					// 可以根据这个数值 和 maximumPoolSize 进行比较, 知道线程池是否已经满了
				", taskCount = " + this.getTaskCount() +								// 需要完成的任务数量
				", completedTaskCount = " + this.getCompletedTaskCount() +				// 已经完成的任务数量
				", activeCount = " + this.getActiveCount());							// 当前线程池中存活的线程数量
		super.terminated();
	}
}
