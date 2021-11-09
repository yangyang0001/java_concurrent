/**
 * 甲骨文专有/机密。 使用受许可条款的约束。
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * 由 Doug Lea 在 JCP JSR-166 专家组成员的协助下撰写并发布到公共领域，如 http://creativecommons.org/publicdomain/zero/1.0/ 所述
 * Written by Doug Lea with assistance from members of JCP JSR-166 Expert Group and released to the public domain, as explained at http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_001;
import sun.misc.Unsafe;

/**
 * 用于创建锁和其他同步类的基本线程阻塞原语。 TODO 创建锁的单位正交基
 * Basic thread blocking primitives for creating locks and other synchronization classes.
 *
 * <p>
 * 此类与使用它的每个线程关联一个许可（在 {@link java.util.concurrent.Semaphore Semaphore} 类的意义上）。
 * This class associates, with each thread that uses it, a permit (in the sense of the {@link java.util.concurrent.Semaphore Semaphore} class).
 *
 * 如果许可证可用，调用 {@code park} 将立即返回，并在此过程中使用它；否则它<em>可能</em>阻塞。
 * A call to {@code park} will return immediately if the permit is available, consuming it in the process; otherwise it <em>may</em> block.
 *
 * 如果许可证尚未可用，则调用 {@code unpark} 可使许可证可用。
 * A call to {@code unpark} makes the permit available, if it was not already available.
 *
 * （尽管与信号量不同，许可不会累积。最多只有一个。）
 * (Unlike with Semaphores though, permits do not accumulate. There is at most one.)
 *
 * <p>
 * 方法 {@code park} 和 {@code unpark} 提供了阻塞和解除阻塞线程的有效方法，这些线程不会遇到导致已弃用的方法 {@code Thread.suspend} 和 {@code Thread.resume} 无法用于的问题此类目的：在一个调用 {@code park} 的线程和另一个试图 {@code unpark} 的线程之间进行竞争，由于许可，它将保持活力。
 * Methods {@code park} and {@code unpark} provide efficient means of blocking and unblocking threads that do not encounter the problems that cause the deprecated methods {@code Thread.suspend} and {@code Thread.resume} to be unusable for such purposes: Races between one thread invoking {@code park} and another thread trying to {@code unpark} it will preserve liveness, due to the permit.
 *
 * 此外，如果调用者的线程被中断，{@code park} 将返回，并且支持超时版本。
 * Additionally, {@code park} will return if the caller's thread was interrupted, and timeout versions are supported.
 *
 * {@code park} 方法也可以在任何其他时间返回，“无缘无故”，因此通常必须在返回时重新检查条件的循环中调用。
 * The {@code park} method may also return at any other time, for "no reason", so in general must be invoked within a loop that rechecks conditions upon return.
 *
 * 从这个意义上说，{@code park} 是“忙等待”的优化，不会浪费太多时间旋转，但必须与 {@code unpark} 配对才能有效。
 * In this sense {@code park} serves as an optimization of a "busy wait" that does not waste as much time spinning, but must be paired with an {@code unpark} to be effective.
 *
 * <p>
 * {@code park} 的三种形式都支持 {@code blocker} 对象参数。
 * The three forms of {@code park} each also support a {@code blocker} object parameter.
 *
 * 该对象在线程被阻塞时被记录，以允许监控和诊断工具识别线程被阻塞的原因。
 * This object is recorded while the thread is blocked to permit monitoring and diagnostic tools to identify the reasons that threads are blocked.
 *
 *（此类工具可以使用方法 {@link #getBlocker(Thread)} 访问阻止程序。）
 * (Such tools may access blockers using method {@link #getBlocker(Thread)}.)
 *
 * 强烈鼓励使用这些表格而不是没有此参数的原始表格。
 * The use of these forms rather than the original forms without this parameter is strongly encouraged.
 *
 * 在锁实现中作为 {@code blocker} 提供的正常参数是 {@code this}。
 * The normal argument to supply as a {@code blocker} within a lock implementation is {@code this}.
 *
 * <p>
 * 这些方法旨在用作创建更高级别同步实用程序的工具，并且它们本身对大多数并发控制应用程序没有用处。
 * These methods are designed to be used as tools for creating higher-level synchronization utilities, and are not in themselves useful for most concurrency control applications.
 *
 * {@code park} 方法仅用于以下形式的构造：
 * The {@code park} method is designed for use only in constructions of the form:
 *
 * <pre>
 * {@code while (!canProceed()) { ... LockSupport.park(this); }}
 * </pre>
 *
 * 在调用 {@code park} 之前，{@code canProceed} 或任何其他操作都不需要锁定或阻止。
 * where neither {@code canProceed} nor any other actions prior to the call to {@code park} entail locking or blocking.
 *
 * 由于每个线程只关联一个许可证，因此对 {@code park} 的任何中间使用都可能干扰其预期效果。
 * Because only one permit is associated with each thread, any intermediary uses of {@code park} could interfere with its intended effects.
 *
 * <p>
 * <b>Sample Usage.</b> Here is a sketch of a first-in-first-out non-reentrant lock class:
 * <pre>
 * {@code
 *      class FIFOMutex {
 *        private final AtomicBoolean locked = new AtomicBoolean(false);
 *        private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();
 *
 *        public void lock() {
 *          boolean wasInterrupted = false;
 *          Thread current = Thread.currentThread();
 *          waiters.add(current);
 *
 *          // Block while not first in queue or cannot acquire lock
 *          while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
 *            LockSupport.park(this);
 *            if (Thread.interrupted()) // ignore interrupts while waiting
 *              wasInterrupted = true;
 *          }
 *
 *          waiters.remove();
 *          if (wasInterrupted)          // reassert interrupt status on exit
 *            current.interrupt();
 *        }
 *
 *        public void unlock() {
 *          locked.set(false);
 *          LockSupport.unpark(waiters.peek());
 *        }
 *      }
 * }</pre>
 */
public class LockSupport {
    private LockSupport() {} // Cannot be instantiated.

    private static void setBlocker(Thread t, Object arg) {
        // Even though volatile, hotspot doesn't need a write barrier here.
        UNSAFE.putObject(t, parkBlockerOffset, arg);
    }

    /**
     * Makes available the permit for the given thread, if it
     * was not already available.  If the thread was blocked on
     * {@code park} then it will unblock.  Otherwise, its next call
     * to {@code park} is guaranteed not to block. This operation
     * is not guaranteed to have any effect at all if the given
     * thread has not been started.
     *
     * @param thread the thread to unpark, or {@code null}, in which case
     *        this operation has no effect
     */
    public static void unpark(Thread thread) {
        if (thread != null)
            UNSAFE.unpark(thread);
    }

    /**
     * 除非许可可用，否则出于线程调度目的禁用当前线程。
     * Disables the current thread for thread scheduling purposes unless the permit is available.
     *
     * <p>
     * 如果许可可用，则它被消耗并且调用立即返回；
     * If the permit is available then it is consumed and the call returns immediately;
     *
     * 否则，当前线程将因线程调度目的而被禁用并处于休眠状态，直到发生以下三种情况之一：
     * otherwise the current thread becomes disabled for thread scheduling purposes and lies dormant until one of three things happens:
     *
     * <ul>
     *      其他线程以当前线程为目标调用 {@link #unpark unpark}； 或者
     * <li> Some other thread invokes {@link #unpark unpark} with the current thread as the target; or
     *
     *      其他线程{@linkplain Thread#interrupt interrupts} 当前线程； 或者
     * <li> Some other thread {@linkplain Thread#interrupt interrupts} the current thread; or
     *
     *      虚假调用（即无缘无故）返回。 TODO 又叫 虚假唤醒
     * <li> The call spuriously (that is, for no reason) returns.
     * </ul>
     *
     * <p>
     * 此方法<em>不</em>报告哪些导致方法返回。
     * This method does <em>not</em> report which of these caused the method to return.
     *
     * 调用者应该首先重新检查导致线程停放的条件。
     * Callers should re-check the conditions which caused the thread to park in the first place.
     *
     * 例如，调用者还可以确定线程在返回时的中断状态。
     * Callers may also determine, for example, the interrupt status of the thread upon return.
     *
     *                负责此线程停放的同步对象
     * @param blocker the synchronization object responsible for this thread parking
     * @since 1.6
     */
    public static void park(Object blocker) {
        Thread t = Thread.currentThread();
        setBlocker(t, blocker);
        UNSAFE.park(false, 0L);
        setBlocker(t, null);
    }

    /**
     * 为线程调度目的禁用当前线程，最长可达指定的等待时间，除非许可可用。
     * Disables the current thread for thread scheduling purposes, for up to the specified waiting time, unless the permit is available.
     *
     * <p>
     *
     * 如果许可可用，则它被消耗并且调用立即返回；
     * If the permit is available then it is consumed and the call returns immediately;
     *
     * 否则，当前线程将因线程调度目的而被禁用并处于休眠状态，直到发生以下四种情况之一：
     * otherwise the current thread becomes disabled for thread scheduling purposes and lies dormant until one of four things happens:
     *
     * <ul>
     *     其他一些线程以当前线程为目标调用 {@link #unpark unpark}； 或者
     * <li>Some other thread invokes {@link #unpark unpark} with the current thread as the target; or
     *
     *     一些其他线程{@linkplain Thread#interrupt interrupts}当前线程； 或者
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the current thread; or
     *
     *     指定的等待时间已过； 或者
     * <li>The specified waiting time elapses; or
     *
     *     虚假调用（即无缘无故）返回。
     * <li>The call spuriously (that is, for no reason) returns.
     * </ul>
     *
     * <p>
     * 此方法<em>不</em>报告哪些导致方法返回。
     * This method does <em>not</em> report which of these caused the method to return.
     *
     * 调用者应该首先重新检查导致线程停放的条件。
     * Callers should re-check the conditions which caused the thread to park in the first place.
     *
     * 例如，调用者还可以确定线程的中断状态或返回时经过的时间。
     * Callers may also determine, for example, the interrupt status of the thread, or the elapsed time upon return.
     *
     * @param blocker the synchronization object responsible for this thread parking
     * @param nanos the maximum number of nanoseconds to wait
     * @since 1.6
     */
    public static void parkNanos(Object blocker, long nanos) {
        if (nanos > 0) {
            Thread t = Thread.currentThread();
            setBlocker(t, blocker);
            UNSAFE.park(false, nanos);
            setBlocker(t, null);
        }
    }

    /**
     * Disables the current thread for thread scheduling purposes, until
     * the specified deadline, unless the permit is available.
     *
     * <p>If the permit is available then it is consumed and the call
     * returns immediately; otherwise the current thread becomes disabled
     * for thread scheduling purposes and lies dormant until one of four
     * things happens:
     *
     * <ul>
     * <li>Some other thread invokes {@link #unpark unpark} with the
     * current thread as the target; or
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread; or
     *
     * <li>The specified deadline passes; or
     *
     * <li>The call spuriously (that is, for no reason) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these caused the
     * method to return. Callers should re-check the conditions which caused
     * the thread to park in the first place. Callers may also determine,
     * for example, the interrupt status of the thread, or the current time
     * upon return.
     *
     * @param blocker the synchronization object responsible for this
     *        thread parking
     * @param deadline the absolute time, in milliseconds from the Epoch,
     *        to wait until
     * @since 1.6
     */
    public static void parkUntil(Object blocker, long deadline) {
        Thread t = Thread.currentThread();
        setBlocker(t, blocker);
        UNSAFE.park(true, deadline);
        setBlocker(t, null);
    }

    /**
     * Returns the blocker object supplied to the most recent
     * invocation of a park method that has not yet unblocked, or null
     * if not blocked.  The value returned is just a momentary
     * snapshot -- the thread may have since unblocked or blocked on a
     * different blocker object.
     *
     * @param t the thread
     * @return the blocker
     * @throws NullPointerException if argument is null
     * @since 1.6
     */
    public static Object getBlocker(Thread t) {
        if (t == null)
            throw new NullPointerException();
        return UNSAFE.getObjectVolatile(t, parkBlockerOffset);
    }

    /**
     * Disables the current thread for thread scheduling purposes unless the
     * permit is available.
     *
     * <p>If the permit is available then it is consumed and the call
     * returns immediately; otherwise the current thread becomes disabled
     * for thread scheduling purposes and lies dormant until one of three
     * things happens:
     *
     * <ul>
     *
     * <li>Some other thread invokes {@link #unpark unpark} with the
     * current thread as the target; or
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts}
     * the current thread; or
     *
     * <li>The call spuriously (that is, for no reason) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these caused the
     * method to return. Callers should re-check the conditions which caused
     * the thread to park in the first place. Callers may also determine,
     * for example, the interrupt status of the thread upon return.
     */
    public static void park() {
        UNSAFE.park(false, 0L);
    }

    /**
     * Disables the current thread for thread scheduling purposes, for up to
     * the specified waiting time, unless the permit is available.
     *
     * <p>If the permit is available then it is consumed and the call
     * returns immediately; otherwise the current thread becomes disabled
     * for thread scheduling purposes and lies dormant until one of four
     * things happens:
     *
     * <ul>
     * <li>Some other thread invokes {@link #unpark unpark} with the
     * current thread as the target; or
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts}
     * the current thread; or
     *
     * <li>The specified waiting time elapses; or
     *
     * <li>The call spuriously (that is, for no reason) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these caused the
     * method to return. Callers should re-check the conditions which caused
     * the thread to park in the first place. Callers may also determine,
     * for example, the interrupt status of the thread, or the elapsed time
     * upon return.
     *
     * @param nanos the maximum number of nanoseconds to wait
     */
    public static void parkNanos(long nanos) {
        if (nanos > 0)
            UNSAFE.park(false, nanos);
    }

    /**
     * Disables the current thread for thread scheduling purposes, until
     * the specified deadline, unless the permit is available.
     *
     * <p>If the permit is available then it is consumed and the call
     * returns immediately; otherwise the current thread becomes disabled
     * for thread scheduling purposes and lies dormant until one of four
     * things happens:
     *
     * <ul>
     * <li>Some other thread invokes {@link #unpark unpark} with the
     * current thread as the target; or
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts}
     * the current thread; or
     *
     * <li>The specified deadline passes; or
     *
     * <li>The call spuriously (that is, for no reason) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these caused the
     * method to return. Callers should re-check the conditions which caused
     * the thread to park in the first place. Callers may also determine,
     * for example, the interrupt status of the thread, or the current time
     * upon return.
     *
     * @param deadline the absolute time, in milliseconds from the Epoch,
     *        to wait until
     */
    public static void parkUntil(long deadline) {
        UNSAFE.park(true, deadline);
    }

    /**
     * Returns the pseudo-randomly initialized or updated secondary seed.
     * Copied from ThreadLocalRandom due to package access restrictions.
     */
    static final int nextSecondarySeed() {
        int r;
        Thread t = Thread.currentThread();
        if ((r = UNSAFE.getInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorshift
            r ^= r >>> 17;
            r ^= r << 5;
        }
        else if ((r = java.util.concurrent.ThreadLocalRandom.current().nextInt()) == 0)
            r = 1; // avoid zero
        UNSAFE.putInt(t, SECONDARY, r);
        return r;
    }

    // Hotspot implementation via intrinsics API
    private static final Unsafe UNSAFE;
    private static final long parkBlockerOffset;
    private static final long SEED;
    private static final long PROBE;
    private static final long SECONDARY;
    static {
        try {
            UNSAFE = Unsafe.getUnsafe();
            Class<?> tk = Thread.class;
            parkBlockerOffset = UNSAFE.objectFieldOffset
                (tk.getDeclaredField("parkBlocker"));
            SEED = UNSAFE.objectFieldOffset
                (tk.getDeclaredField("threadLocalRandomSeed"));
            PROBE = UNSAFE.objectFieldOffset
                (tk.getDeclaredField("threadLocalRandomProbe"));
            SECONDARY = UNSAFE.objectFieldOffset
                (tk.getDeclaredField("threadLocalRandomSecondarySeed"));
        } catch (Exception ex) { throw new Error(ex); }
    }

}
