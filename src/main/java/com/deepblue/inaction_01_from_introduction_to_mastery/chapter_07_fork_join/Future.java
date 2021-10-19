package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_07_fork_join;

/**
 * 甲骨文专有/机密。 使用受许可条款的约束。
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * 由 Doug Lea 在 JCP JSR-166 专家组成员的协助下撰写并发布到公共领域，如 http://creativecommons.org/publicdomain/zero/1.0/ 所述
 * Written by Doug Lea with assistance from members of JCP JSR-166 Expert Group and released to the public domain, as explained at http://creativecommons.org/publicdomain/zero/1.0/
 */

import java.util.concurrent.*;

/**
 * {@code Future} 表示异步计算的结果。
 * A {@code Future} represents the result of an asynchronous computation.
 *
 * 提供了检查计算是否完成、等待计算完成以及检索计算结果的方法。
 * Methods are provided to check if the computation is complete, to wait for its completion, and to retrieve the result of the computation.
 *
 * 结果只能在计算完成后使用方法 {@code get} 检索，必要时阻塞直到它准备好。 TODO get方法是阻塞的, 直到获取到结果之前!
 * The result can only be retrieved using method {@code get} when the computation has completed, blocking if necessary until it is ready.
 *
 * 取消由 {@code cancel} 方法执行。
 * Cancellation is performed by the {@code cancel} method.
 *
 * 提供了其他方法来确定任务是正常完成还是被取消。
 * Additional methods are provided to determine if the task completed normally or was cancelled.
 *
 * 一旦计算完成，就不能取消计算。
 * Once a computation has completed, the computation cannot be cancelled.
 *
 * 如果为了可取消性而想使用 {@code Future} 但不提供可用的结果，您可以声明形式 {@code Future<?>} 的类型并返回 {@code null} 作为结果 基础任务。
 * If you would like to use a {@code Future} for the sake of cancellability but not provide a usable result, you can declare types of the form {@code Future<?>} and return {@code null} as a result of the underlying task.
 *
 * <p>
 * <b>Sample Usage</b> (Note that the following classes are all
 * made-up.)
 * <pre> {@code
 * interface ArchiveSearcher { String search(String target); }
 * class App {
 *   ExecutorService executor = ...
 *   ArchiveSearcher searcher = ...
 *   void showSearch(final String target)
 *       throws InterruptedException {
 *     Future<String> future
 *       = executor.submit(new Callable<String>() {
 *         public String call() {
 *             return searcher.search(target);
 *         }});
 *     displayOtherThings(); // do other things while searching
 *     try {
 *       displayText(future.get()); // use future
 *     } catch (ExecutionException ex) { cleanup(); return; }
 *   }
 * }}</pre>
 *
 * {@link FutureTask} 类是实现 {@code Runnable} 的 {@code Future} 的实现，因此可以由 {@code Executor} 执行。
 * The {@link FutureTask} class is an implementation of {@code Future} that implements {@code Runnable}, and so may be executed by an {@code Executor}.
 *
 * For example, the above construction with {@code submit} could be replaced by:
 *  <pre> {@code
 * FutureTask<String> future =
 *   new FutureTask<String>(new Callable<String>() {
 *     public String call() {
 *       return searcher.search(target);
 *   }});
 * executor.execute(future);}</pre>
 *
 * <p>
 * 内存一致性影响：异步计算采取的操作发生在另一个线程中相应的 {@code Future.get()} 之后的操作之前。
 * Memory consistency effects: Actions taken by the asynchronous computation happen-before actions following the corresponding {@code Future.get()} in another thread.
 *
 * @see FutureTask
 * @see Executor
 * @since 1.5
 * @author Doug Lea
 * @param <V> The result type returned by this Future's {@code get} method
 */
public interface Future<V> {

	/**
	 * 尝试取消此任务的执行。
	 * Attempts to cancel execution of this task.
	 *
	 * 如果任务已完成、已被取消或由于其他原因无法取消，则此尝试将失败。
	 * This attempt will fail if the task has already completed, has already been cancelled, or could not be cancelled for some other reason.
	 *
	 * 如果成功，并且在调用 {@code cancel} 时此任务尚未启动，则不应运行此任务。
	 * If successful, and this task has not started when {@code cancel} is called, this task should never run.
	 *
	 * 如果任务已经开始，那么 {@code mayInterruptIfRunning} 参数决定是否应该中断执行该任务的线程以尝试停止该任务。
	 * If the task has already started, then the {@code mayInterruptIfRunning} parameter determines whether the thread executing this task should be interrupted in an attempt to stop the task.
	 *
	 * <p>
	 * 此方法返回后，对 {@link #isDone} 的后续调用将始终返回 {@code true}。
	 * After this method returns, subsequent calls to {@link #isDone} will always return {@code true}.
	 *
	 * 如果此方法返回 {@code true}，则对 {@link #isCancelled} 的后续调用将始终返回 {@code true}。
	 * Subsequent calls to {@link #isCancelled} will always return {@code true} if this method returned {@code true}.
	 *
	 * @param mayInterruptIfRunning
	 * 看下这个方法 在txt 文件中对 cancel(boolean mayInterruptRunning) 解释!
	 * {@code true} if the thread executing this task should be interrupted; otherwise, in-progress tasks are allowed to complete @return {@code false} if the task could not be cancelled, typically because it has already completed normally; {@code true} otherwise
	 */
	boolean cancel(boolean mayInterruptIfRunning);

	/**
	 * 如果此任务在正常完成之前被取消，则返回 {@code true}。
	 * Returns {@code true} if this task was cancelled before it completed normally.
	 *
	 * @return {@code true} 如果此任务在完成之前被取消
	 * @return {@code true} if this task was cancelled before it completed
	 */
	boolean isCancelled();

	/**
	 * 如果此任务完成，则返回 {@code true}。
	 * Returns {@code true} if this task completed.
	 *
	 * 完成可能是由于正常终止、异常或取消——在所有这些情况下，此方法将返回 {@code true}。
	 * Completion may be due to normal termination, an exception, or cancellation -- in all of these cases, this method will return {@code true}.
	 *
	 * @return {@code true} if this task completed
	 */
	boolean isDone();

	/**
	 * 如有必要，等待计算完成，然后检索其结果。
	 * Waits if necessary for the computation to complete, and then retrieves its result.
	 *
	 * @return the computed result
	 * @throws CancellationException if the computation was cancelled
	 * @throws ExecutionException if the computation threw an exception
	 * @throws InterruptedException if the current thread was interrupted while waiting
	 */
	V get() throws InterruptedException, ExecutionException;

	/**
	 * 如有必要，最多等待给定的计算完成时间，然后检索其结果（如果可用）。
	 * Waits if necessary for at most the given time for the computation to complete, and then retrieves its result, if available.
	 *
	 * @param timeout the maximum time to wait
	 * @param unit the time unit of the timeout argument
	 * @return the computed result
	 * @throws CancellationException if the computation was cancelled
	 * @throws ExecutionException if the computation threw an exception
	 * @throws InterruptedException if the current thread was interrupted while waiting
	 * @throws TimeoutException if the wait timed out
	 */
	V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
}
