/**
 * 甲骨文专有/机密。 使用受许可条款的约束。
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * 由 Doug Lea 在 JCP JSR-166 专家组成员的协助下撰写并发布到公共领域，如 http://creativecommons.org/publicdomain/zero/1.0/ 所述
 * Written by Doug Lea with assistance from members of JCP JSR-166 Expert Group and released to the public domain, as explained at http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_05_thread_valve;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * {@code Condition} 将 {@code Object} 监控方法（{@link Object#wait() wait}、{@link Object#notify notify} 和 {@link Object#notifyAll notifyAll}）分解为不同的对象以给出 通过将它们与任意 {@link Lock} 实现的使用相结合，每个对象具有多个等待集的效果。
 * {@code Condition} factors out the {@code Object} monitor methods ({@link Object#wait() wait}, {@link Object#notify notify} and {@link Object#notifyAll notifyAll}) into distinct objects to give the effect of having multiple wait-sets per object, by combining them with the use of arbitrary {@link Lock} implementations.
 *
 * {@code Lock} 代替了 {@code synchronized} 方法和语句的使用，{@code Condition} 代替了对象监视器方法的使用。
 * Where a {@code Lock} replaces the use of {@code synchronized} methods and statements, a {@code Condition} replaces the use of the Object monitor methods.
 *
 * <p>
 *
 * 条件（也称为<em>条件队列</em>或<em>条件变量</em>）为一个线程提供了一种挂起执行（“等待”）的方法，直到另一个线程通知某个状态条件 现在可能是真的。
 * Conditions (also known as <em>condition queues</em> or <em>condition variables</em>) provide a means for one thread to suspend execution (to &quot;wait&quot;) until notified by another thread that some state condition may now be true.
 *
 * 因为对这个共享状态信息的访问发生在不同的线程中，它必须受到保护，所以某种形式的锁与条件相关联。
 * Because access to this shared state information occurs in different threads, it must be protected, so a lock of some form is associated with the condition.
 *
 * 等待条件提供的关键属性是它<em>原子地</em>释放关联的锁并挂起当前线程，就像 {@code Object.wait} 一样。
 * The key property that waiting for a condition provides is that it <em>atomically</em> releases the associated lock and suspends the current thread, just like {@code Object.wait}.
 *
 * <p>
 *
 * {@code Condition} 实例本质上绑定到锁。
 * A {@code Condition} instance is intrinsically bound to a lock.
 *
 * 要获取特定 {@link Lock} 实例的 {@code Condition} 实例，请使用其 {@link Lock#newCondition newCondition()} 方法。
 * To obtain a {@code Condition} instance for a particular {@link Lock} instance use its {@link Lock#newCondition newCondition()} method.
 *
 * <p>
 *
 * 例如，假设我们有一个支持 {@code put} 和 {@code take} 方法的有界缓冲区。
 * As an example, suppose we have a bounded buffer which supports {@code put} and {@code take} methods.
 *
 * 如果在空缓冲区上尝试 {@code take}，则线程将阻塞，直到项目可用； 如果在一个完整的缓冲区上尝试 {@code put}，则线程将阻塞，直到有可用空间为止。
 * If a {@code take} is attempted on an empty buffer, then the thread will block until an item becomes available; if a {@code put} is attempted on a full buffer, then the thread will block until a space becomes available.
 *
 * 我们希望在不同的等待集中继续等待 {@code put} 线程和 {@code take} 线程，以便我们可以使用优化，当缓冲区中的项目或空间变得可用时，一次只通知一个线程。
 * We would like to keep waiting {@code put} threads and {@code take} threads in separate wait-sets so that we can use the optimization of only notifying a single thread at a time when items or spaces become available in the buffer.
 *
 * 这可以使用两个 {@link Condition} 实例来实现。
 * This can be achieved using two {@link Condition} instances.
 *
 * <pre>
 * class BoundedBuffer {
 *   <b>final Lock lock = new ReentrantLock();</b>
 *   final Condition notFull  = <b>lock.newCondition(); </b>
 *   final Condition notEmpty = <b>lock.newCondition(); </b>
 *
 *   final Object[] items = new Object[100];
 *   int putptr, takeptr, count;
 *
 *   public void put(Object x) throws InterruptedException {
 *     <b>lock.lock();
 *     try {</b>
 *       while (count == items.length)
 *         <b>notFull.await();</b>
 *       items[putptr] = x;
 *       if (++putptr == items.length) putptr = 0;
 *       ++count;
 *       <b>notEmpty.signal();</b>
 *     <b>} finally {
 *       lock.unlock();
 *     }</b>
 *   }
 *
 *   public Object take() throws InterruptedException {
 *     <b>lock.lock();
 *     try {</b>
 *       while (count == 0)
 *         <b>notEmpty.await();</b>
 *       Object x = items[takeptr];
 *       if (++takeptr == items.length) takeptr = 0;
 *       --count;
 *       <b>notFull.signal();</b>
 *       return x;
 *     <b>} finally {
 *       lock.unlock();
 *     }</b>
 *   }
 * }
 * </pre>
 * （{@link java.util.concurrent.ArrayBlockingQueue} 类提供此功能，因此没有理由实现此示例用法类。）
 * (The {@link java.util.concurrent.ArrayBlockingQueue} class provides this functionality, so there is no reason to implement this sample usage class.)
 *
 * <p>
 * {@code Condition} 实现可以提供与 {@code Object} 监视器方法不同的行为和语义，例如保证通知的顺序，或者在执行通知时不需要持有锁。
 * A {@code Condition} implementation can provide behavior and semantics that is different from that of the {@code Object} monitor methods, such as guaranteed ordering for notifications, or not requiring a lock to be held when performing notifications.
 *
 * 如果实现提供了这样的专门语义，那么实现必须记录这些语义。
 * If an implementation provides such specialized semantics then the implementation must document those semantics.
 *
 * <p>
 *
 * 请注意，{@code Condition} 实例只是普通对象，它们本身可以用作 {@code synchronized} 语句中的目标，并且可以拥有自己的监视器 {@link Object#wait wait} 和 {@link Object#notify 通知} 方法调用。
 * Note that {@code Condition} instances are just normal objects and can themselves be used as the target in a {@code synchronized} statement, and can have their own monitor {@link Object#wait wait} and {@link Object#notify notification} methods invoked.
 *
 * 获取 {@code Condition} 实例的监视器锁，或使用其监视器方法，与获取与该 {@code Condition} 关联的 {@link Lock} 或使用其 {@linkplain #await waiting } 和 {@linkplain #signal 信号} 方法。
 * Acquiring the monitor lock of a {@code Condition} instance, or using its monitor methods, has no specified relationship with acquiring the {@link Lock} associated with that {@code Condition} or the use of its {@linkplain #await waiting} and {@linkplain #signal signalling} methods.
 *
 * 建议您不要以这种方式使用 {@code Condition} 实例以避免混淆，除非在它们自己的实现中。
 * It is recommended that to avoid confusion you never use {@code Condition} instances in this way, except perhaps within their own implementation.
 *
 * <p>
 * 除非另有说明，否则为任何参数传递 {@code null} 值将导致抛出 {@link NullPointerException}。
 * Except where noted, passing a {@code null} value for any parameter will result in a {@link NullPointerException} being thrown.
 *
 * <h3>实施注意事项</h3>
 * <h3>Implementation Considerations</h3>
 *
 * <p>
 *
 * 当等待 {@code Condition} 时，“<em>虚假唤醒</em>”通常，作为对底层平台语义的让步，允许发生。
 * When waiting upon a {@code Condition}, a &quot;<em>spurious wakeup</em>&quot; is permitted to occur, in general, as a concession to the underlying platform semantics.
 *
 * 这对大多数应用程序几乎没有实际影响，因为 {@code Condition} 应该始终在循环中等待，测试正在等待的状态谓词。
 * This has little practical impact on most application programs as a {@code Condition} should always be waited upon in a loop, testing the state predicate that is being waited for.
 *
 * 实现可以自由地消除虚假唤醒的可能性，但建议应用程序程序员始终假设它们可能发生，因此始终在循环中等待。TODO 处理虚假唤醒的 标杆处理方式
 * An implementation is free to remove the possibility of spurious wakeups but it is recommended that applications programmers always assume that they can occur and so always wait in a loop.
 *
 * <p>
 *
 * 三种形式的条件等待（可中断、不可中断和定时）在某些平台上的实现难易程度和性能特征方面可能有所不同。
 * The three forms of condition waiting (interruptible, non-interruptible, and timed) may differ in their ease of implementation on some platforms and in their performance characteristics.
 *
 * 特别是，可能很难提供这些功能并维护特定的语义，例如排序保证。
 * In particular, it may be difficult to provide these features and maintain specific semantics such as ordering guarantees.
 *
 * 此外，在所有平台上实现中断线程实际挂起的能力并不总是可行的。
 * Further, the ability to interrupt the actual suspension of the thread may not always be feasible to implement on all platforms.
 *
 * <p>
 *
 * 因此，实现不需要为所有三种等待形式定义完全相同的保证或语义，也不需要支持线程实际挂起的中断。
 * Consequently, an implementation is not required to define exactly the same guarantees or semantics for all three forms of waiting, nor is it required to support interruption of the actual suspension of the thread.
 *
 * <p>
 * 一个实现需要清楚地记录每个等待方法提供的语义和保证，当一个实现确实支持线程挂起的中断时，它必须遵守这个接口中定义的中断语义。
 * An implementation is required to clearly document the semantics and guarantees provided by each of the waiting methods, and when an implementation does support interruption of thread suspension then it must obey the interruption semantics as defined in this interface.
 *
 * <p>
 *
 * 由于中断通常意味着取消，并且中断检查通常很少，因此实现可以倾向于响应中断而不是正常的方法返回。
 * As interruption generally implies cancellation, and checks for interruption are often infrequent, an implementation can favor responding to an interrupt over normal method return.
 *
 * 即使可以证明中断发生在另一个可能已解除线程阻塞的操作之后也是如此。
 * This is true even if it can be shown that the interrupt occurred after another action that may have unblocked the thread.
 *
 * 实现应记录此行为。
 * An implementation should document this behavior.
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface Condition {

    /**
     * Causes the current thread to wait until it is signalled or
     * {@linkplain Thread#interrupt interrupted}.
     *
     * <p>The lock associated with this {@code Condition} is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of four things happens:
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of thread suspension is supported; or
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while waiting
     * and interruption of thread suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared. It is not specified, in the first
     * case, whether or not the test for interruption occurs before the lock
     * is released.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return in response to a signal. In that case the implementation
     * must ensure that the signal is redirected to another waiting thread, if
     * there is one.
     *
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    void await() throws InterruptedException;

    /**
     * Causes the current thread to wait until it is signalled.
     *
     * <p>The lock associated with this condition is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of three things happens:
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     *
     * <p>If the current thread's interrupted status is set when it enters
     * this method, or it is {@linkplain Thread#interrupt interrupted}
     * while waiting, it will continue to wait until signalled. When it finally
     * returns from this method its interrupted status will still
     * be set.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     */
    void awaitUninterruptibly();

    /**
     * Causes the current thread to wait until it is signalled or interrupted,
     * or the specified waiting time elapses.
     *
     * <p>The lock associated with this condition is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of five things happens:
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of thread suspension is supported; or
     * <li>The specified waiting time elapses; or
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while waiting
     * and interruption of thread suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared. It is not specified, in the first
     * case, whether or not the test for interruption occurs before the lock
     * is released.
     *
     * <p>The method returns an estimate of the number of nanoseconds
     * remaining to wait given the supplied {@code nanosTimeout}
     * value upon return, or a value less than or equal to zero if it
     * timed out. This value can be used to determine whether and how
     * long to re-wait in cases where the wait returns but an awaited
     * condition still does not hold. Typical uses of this method take
     * the following form:
     *
     *  <pre> {@code
     * boolean aMethod(long timeout, TimeUnit unit) {
     *   long nanos = unit.toNanos(timeout);
     *   lock.lock();
     *   try {
     *     while (!conditionBeingWaitedFor()) {
     *       if (nanos <= 0L)
     *         return false;
     *       nanos = theCondition.awaitNanos(nanos);
     *     }
     *     // ...
     *   } finally {
     *     lock.unlock();
     *   }
     * }}</pre>
     *
     * <p>Design note: This method requires a nanosecond argument so
     * as to avoid truncation errors in reporting remaining times.
     * Such precision loss would make it difficult for programmers to
     * ensure that total waiting times are not systematically shorter
     * than specified when re-waits occur.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return in response to a signal, or over indicating the elapse
     * of the specified waiting time. In either case the implementation
     * must ensure that the signal is redirected to another waiting thread, if
     * there is one.
     *
     * @param nanosTimeout the maximum time to wait, in nanoseconds
     * @return an estimate of the {@code nanosTimeout} value minus
     *         the time spent waiting upon return from this method.
     *         A positive value may be used as the argument to a
     *         subsequent call to this method to finish waiting out
     *         the desired time.  A value less than or equal to zero
     *         indicates that no time remains.
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    long awaitNanos(long nanosTimeout) throws InterruptedException;

    /**
     * Causes the current thread to wait until it is signalled or interrupted,
     * or the specified waiting time elapses. This method is behaviorally
     * equivalent to:
     *  <pre> {@code awaitNanos(unit.toNanos(time)) > 0}</pre>
     *
     * @param time the maximum time to wait
     * @param unit the time unit of the {@code time} argument
     * @return {@code false} if the waiting time detectably elapsed
     *         before return from the method, else {@code true}
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    boolean await(long time, TimeUnit unit) throws InterruptedException;

    /**
     * Causes the current thread to wait until it is signalled or interrupted,
     * or the specified deadline elapses.
     *
     * <p>The lock associated with this condition is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of five things happens:
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of thread suspension is supported; or
     * <li>The specified deadline elapses; or
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     *
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while waiting
     * and interruption of thread suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared. It is not specified, in the first
     * case, whether or not the test for interruption occurs before the lock
     * is released.
     *
     *
     * <p>The return value indicates whether the deadline has elapsed,
     * which can be used as follows:
     *  <pre> {@code
     * boolean aMethod(Date deadline) {
     *   boolean stillWaiting = true;
     *   lock.lock();
     *   try {
     *     while (!conditionBeingWaitedFor()) {
     *       if (!stillWaiting)
     *         return false;
     *       stillWaiting = theCondition.awaitUntil(deadline);
     *     }
     *     // ...
     *   } finally {
     *     lock.unlock();
     *   }
     * }}</pre>
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return in response to a signal, or over indicating the passing
     * of the specified deadline. In either case the implementation
     * must ensure that the signal is redirected to another waiting thread, if
     * there is one.
     *
     * @param deadline the absolute time to wait until
     * @return {@code false} if the deadline has elapsed upon return, else
     *         {@code true}
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    boolean awaitUntil(Date deadline) throws InterruptedException;

    /**
     * Wakes up one waiting thread.
     *
     * <p>If any threads are waiting on this condition then one
     * is selected for waking up. That thread must then re-acquire the
     * lock before returning from {@code await}.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>An implementation may (and typically does) require that the
     * current thread hold the lock associated with this {@code
     * Condition} when this method is called. Implementations must
     * document this precondition and any actions taken if the lock is
     * not held. Typically, an exception such as {@link
     * IllegalMonitorStateException} will be thrown.
     */
    void signal();

    /**
     * Wakes up all waiting threads.
     *
     * <p>If any threads are waiting on this condition then they are
     * all woken up. Each thread must re-acquire the lock before it can
     * return from {@code await}.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>An implementation may (and typically does) require that the
     * current thread hold the lock associated with this {@code
     * Condition} when this method is called. Implementations must
     * document this precondition and any actions taken if the lock is
     * not held. Typically, an exception such as {@link
     * IllegalMonitorStateException} will be thrown.
     */
    void signalAll();
}
