package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_06_thread_pool.example_002;

import java.security.AccessController;
import java.util.concurrent.*;

/**
 * 执行结果: 线程不受自己名称管控了, 交给 SingleThreadExecutor 线程池处理!
 * thread main running end
 * pool-1-thread-1 running start num = 0
 * pool-1-thread-1 running end   num = 0
 * pool-1-thread-1 running start num = 1
 * pool-1-thread-1 running end   num = 1
 * pool-1-thread-1 running start num = 2
 * pool-1-thread-1 running end   num = 2
 * pool-1-thread-1 running start num = 3
 * pool-1-thread-1 running end   num = 3
 * pool-1-thread-1 running start num = 4
 * pool-1-thread-1 running end   num = 4
 * pool-1-thread-1 running start num = 5
 * pool-1-thread-1 running end   num = 5
 * pool-1-thread-1 running start num = 6
 * pool-1-thread-1 running end   num = 6
 * pool-1-thread-1 running start num = 7
 * pool-1-thread-1 running end   num = 7
 * pool-1-thread-1 running start num = 8
 * pool-1-thread-1 running end   num = 8
 * pool-1-thread-1 running start num = 9
 * pool-1-thread-1 running end   num = 9
 */
public class NewSingleThreadExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();

		for(int i = 0; i < 10; i++) {
			Thread thread = new Thread(new ThreadA(i), "threadA_" + i);
			executor.execute(thread);
		}

		executor.shutdown();

		System.out.println("thread main running end");
	}

	/**
	 * 源码解析:
	 * 使用 给定的初始参数和默认线程工厂 以及 被拒绝的执行处理程序 创建一个新的 {@code ThreadPoolExecutor}。
	 * Creates a new {@code ThreadPoolExecutor} with the given initial parameters and default thread factory and rejected execution handler.
	 *
	 * 使用 {@link Executors} 工厂方法之一代替此通用构造函数可能更方便。
	 * It may be more convenient to use one of the {@link Executors} factory methods instead of this general purpose constructor.
	 *
	 * @param corePoolSize
	 * 		要保留在池中的线程数，即使它们处于空闲状态，除非设置了 {@code allowCoreThreadTimeOut}
	 * 		the number of threads to keep in the pool, even if they are idle, unless {@code allowCoreThreadTimeOut} is set
	 *
	 * @param maximumPoolSize
	 * 		池中允许的最大线程数
	 * 		the maximum number of threads to allow in the pool
	 *
	 * @param keepAliveTime
	 *		当线程数大于 corePoolSize 时，这是多余的空闲线程在终止前等待新任务的最长时间。
	 * 		when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
	 *
	 * @param unit
	 * 		{@code keepAliveTime} 参数的时间单位
	 * 		the time unit for the {@code keepAliveTime} argument
	 *
	 * @param workQueue
	 * 		用于在执行任务之前保存任务的队列。
	 * 		the queue to use for holding tasks before they are executed.
	 *
	 * 		该队列将仅保存由 {@code execute} 方法提交的 {@code Runnable} 任务。
	 *      This queue will hold only the {@code Runnable} tasks submitted by the {@code execute} method.
	 *
	 * @throws IllegalArgumentException if one of the following holds:<br>	如果以下情况之一成立：
	 *         {@code corePoolSize < 0}<br>
	 *         {@code keepAliveTime < 0}<br>
	 *         {@code maximumPoolSize <= 0}<br>
	 *         {@code maximumPoolSize < corePoolSize}
	 * @throws NullPointerException if {@code workQueue} is null
	 *
	 * public ThreadPoolExecutor(int corePoolSize,
	 * 							 int maximumPoolSize,
	 * 							 long keepAliveTime,
	 * 							 TimeUnit unit,
	 * 							 BlockingQueue<Runnable> workQueue) {
	 * 		this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), defaultHandler);
	 * }
	 *
	 * 这个方法中的 this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), defaultHandler) 源码解析如下:
	 */


	/**
	 * 使用给定的初始参数创建一个新的 {@code ThreadPoolExecutor}。
	 * Creates a new {@code ThreadPoolExecutor} with the given initial parameters.
	 *
	 * @param corePoolSize
	 * 		要保留在池中的线程数，即使它们处于空闲状态，除非设置了 {@code allowCoreThreadTimeOut}
	 * 		the number of threads to keep in the pool, even if they are idle, unless {@code allowCoreThreadTimeOut} is set
	 *
	 * @param maximumPoolSize
	 * 		池中允许的最大线程数
	 * 		the maximum number of threads to allow in the pool
	 *
	 * @param keepAliveTime
	 * 		当线程数大于核心数时，这是多余的空闲线程在终止前等待新任务的最长时间。
	 * 		when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
	 *
	 * @param unit
	 * 		{@code keepAliveTime} 参数的时间单位
	 * 		the time unit for the {@code keepAliveTime} argument
	 *
	 * @param workQueue
	 * 		用于在执行任务之前保存任务的队列。
	 * 		the queue to use for holding tasks before they are executed.
	 *
	 *		该队列将仅保存由 {@code execute} 方法提交的 {@code Runnable} 任务。
	 *     	This queue will hold only the {@code Runnable} tasks submitted by the {@code execute} method.
	 *
	 * @param threadFactory
	 * 		执行程序创建新线程时使用的工厂
	 * 		the factory to use when the executor creates a new thread
	 *
	 * @param handler
	 * 		由于达到线程边界和队列容量而阻塞执行时要使用的处理程序
	 * 		the handler to use when execution is blocked because the thread bounds and queue capacities are reached
	 *
	 * 如果以下情况之一成立 则抛出非法参数异常
	 * @throws IllegalArgumentException if one of the following holds:<br>
	 *         {@code corePoolSize < 0}<br>
	 *         {@code keepAliveTime < 0}<br>
	 *         {@code maximumPoolSize <= 0}<br>
	 *         {@code maximumPoolSize < corePoolSize}
	 *
	 * 如果这3个参数中有任意一个为 null, 则抛出空指针异常
	 * @throws NullPointerException if {@code workQueue} or {@code threadFactory} or {@code handler} is null
	 *
	 * public ThreadPoolExecutor(int corePoolSize,
	 * 						  	 int maximumPoolSize,
	 * 						  	 long keepAliveTime,
	 * 						  	 TimeUnit unit,
	 * 						  	 BlockingQueue<Runnable> workQueue,
	 * 						  	 ThreadFactory threadFactory,
	 * 						  	 RejectedExecutionHandler handler)
	 * {
	 * 		if (corePoolSize < 0 || maximumPoolSize <= 0 || maximumPoolSize < corePoolSize || keepAliveTime < 0)
	 * 			throw new IllegalArgumentException();
	 *
	 * 		if (workQueue == null || threadFactory == null || handler == null)
	 * 			throw new NullPointerException();
	 *
	 * 		this.acc = System.getSecurityManager() == null ? null : AccessController.getContext();
	 * 		this.corePoolSize = corePoolSize;
	 * 		this.maximumPoolSize = maximumPoolSize;
	 * 		this.workQueue = workQueue;
	 * 		this.keepAliveTime = unit.toNanos(keepAliveTime);
	 * 		this.threadFactory = threadFactory;
	 * 		this.handler = handler;
	 * }
	 */

}
