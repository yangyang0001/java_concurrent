/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.deepblue.inaction_02_beauty_of_calculation.chapter_04_atomic.example_002;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
//import java.util.concurrent.atomic.Striped64;

/**
 *
 * 一个或多个变量一起保持初始为零的 {@code long} 总和。
 * One or more variables that together maintain an initially zero {@code long} sum.
 *
 * 当更新（方法{@link #add}）跨线程竞争时，变量集可能会动态增长以减少争用。
 * When updates (method {@link #add}) are contended across threads, the set of variables may grow dynamically to reduce contention.
 *
 * 方法 {@link #sum}（或等价的 {@link #longValue}）返回当前 base + Cell 的总和
 * Method {@link #sum} (or, equivalently, {@link #longValue}) returns the current total combined across the variables maintaining the sum.
 *
 * <p>
 * 当多个线程更新用于收集统计信息等目的而不是细粒度同步控制的公共总和时，此类通常比 {@link AtomicLong} 更可取。
 * This class is usually preferable to {@link AtomicLong} when multiple threads update a common sum that is used for purposes such as collecting statistics, not for fine-grained synchronization control.
 *
 * 在低更新竞争下，这两个类具有相似的特征。
 * Under low update contention, the two classes have similar characteristics.
 *
 * 但在高争用情况下，此类的预期吞吐量明显更高，但代价是更高的空间消耗。
 * But under high contention, expected throughput of this class is significantly higher, at the expense of higher space consumption.
 *
 * <p>
 * LongAdders 可以与 {@link java.util.concurrent.ConcurrentHashMap} 一起使用以维护可扩展的频率图（直方图或多重集的形式）。
 * LongAdders can be used with a {@link java.util.concurrent.ConcurrentHashMap} to maintain a scalable frequency map (a form of histogram or multiset).
 *
 * 例如，要将计数添加到 {@code ConcurrentHashMap<String,LongAdder> freqs}，如果尚未存在则进行初始化，您可以使用 {@code freqs.computeIfAbsent(k -> new LongAdder()).increment(); }
 * For example, to add a count to a {@code ConcurrentHashMap<String,LongAdder> freqs}, initializing if not already present, you can use {@code freqs.computeIfAbsent(k -> new LongAdder()).increment();}
 *
 * <p>
 * 此类扩展了 {@link Number}，但<em>没有</em>定义了诸如 {@code equals}、{@code hashCode} 和 {@code compareTo} 之类的方法，因为预计实例会发生变异，因此 不能用作集合键。
 * This class extends {@link Number}, but does <em>not</em> define methods such as {@code equals}, {@code hashCode} and {@code compareTo} because instances are expected to be mutated, and so are not useful as collection keys.
 *
 * @since 1.8
 * @author Doug Lea
 */
public class LongAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    /**
     * 创建一个初始和为零的新加法器。
     * Creates a new adder with initial sum of zero.
     */
    public LongAdder() {
    }

    /**
     * 添加给定的值。
     * Adds the given value.
     *
     * @param x the value to add
     */
    public void add(long x) {
        Cell[] as; long b, v; int m; Cell a;
        if ((as = cells) != null || !casBase(b = base, b + x)) {
            boolean uncontended = true;
            if (as == null || (m = as.length - 1) < 0 ||
                (a = as[getProbe() & m]) == null ||
                !(uncontended = a.cas(v = a.value, v + x)))
                longAccumulate(x, null, uncontended);
        }
    }

    /**
     * Equivalent to {@code add(1)}.
     */
    public void increment() {
        add(1L);
    }

    /**
     * Equivalent to {@code add(-1)}.
     */
    public void decrement() {
        add(-1L);
    }

    /**
     * 返回当前总和。
     * Returns the current sum.
     *
     * 返回值是<em>不是</em>一个原子快照；
     * The returned value is <em>NOT</em> an atomic snapshot;
     *
     * 在没有并发更新的情况下调用会返回准确的结果，但可能不会合并在计算总和时发生的并发更新。
     * invocation in the absence of concurrent updates returns an accurate result, but concurrent updates that occur while the sum is being calculated might not be incorporated.
     *
     * @return the sum
     */
    public long sum() {
        Cell[] as = cells; Cell a;
        long sum = base;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    sum += a.value;
            }
        }
        return sum;
    }

    /**
     * 重置保持总和为零的变量。
     * Resets variables maintaining the sum to zero.
     *
     * 此方法可能是创建新加法器的有用替代方法，但仅在没有并发更新时才有效。
     * This method may be a useful alternative to creating a new adder, but is only effective if there are no concurrent updates.
     *
     * 因为这个方法本质上是活泼的，所以应该只在知道没有线程正在并发更新时使用它。
     * Because this method is intrinsically racy, it should only be used when it is known that no threads are concurrently updating.
     */
    public void reset() {
        Cell[] as = cells; Cell a;
        base = 0L;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    a.value = 0L;
            }
        }
    }

    /**
     * 等效于 {@link #sum} 后跟 {@link #reset}。
     * Equivalent in effect to {@link #sum} followed by {@link #reset}.
     *
     * 例如，该方法可以在多线程计算之间的静止点期间应用。
     * This method may apply for example during quiescent points between multithreaded computations.
     *
     * 如果有与此方法并发的更新，则返回值<em>不</em>保证是重置之前发生的最终值。
     * If there are updates concurrent with this method, the returned value is <em>not</em> guaranteed to be the final value occurring before the reset.
     *
     * @return the sum
     */
    public long sumThenReset() {
        Cell[] as = cells; Cell a;
        long sum = base;
        base = 0L;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    sum += a.value;
                    a.value = 0L;
                }
            }
        }
        return sum;
    }

    /**
     * 返回 {@link #sum} 的字符串表示。
     * Returns the String representation of the {@link #sum}.
     * @return the String representation of the {@link #sum}
     */
    public String toString() {
        return Long.toString(sum());
    }

    /**
     * 相当于 {@link #sum}。
     * Equivalent to {@link #sum}.
     *
     * @return the sum
     */
    public long longValue() {
        return sum();
    }

    /**
     * 将 sum() 值转换为 int 类型后 进行返回
     * Returns the {@link #sum} as an {@code int} after a narrowing primitive conversion.
     */
    public int intValue() {
        return (int)sum();
    }

    /**
     * 将 sum() 值转换为 float 类型后 进行返回
     * Returns the {@link #sum} as a {@code float} after a widening primitive conversion.
     */
    public float floatValue() {
        return (float)sum();
    }

    /**
     * 将 sum() 值转换为 double 类型后 进行返回
     * Returns the {@link #sum} as a {@code double} after a widening primitive conversion.
     */
    public double doubleValue() {
        return (double)sum();
    }

    /**
     * 序列化代理，用于避免以序列化形式引用非公开的 Striped64 超类。
     * Serialization proxy, used to avoid reference to the non-public Striped64 superclass in serialized forms.
     * @serial include
     */
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 7249069246863182397L;

        /**
         * The current value returned by sum().
         * @serial
         */
        private final long value;

        SerializationProxy(LongAdder a) {
            value = a.sum();
        }

        /**
         * 返回一个 {@code LongAdder} 对象，该对象具有此代理保持的初始状态。
         * Return a {@code LongAdder} object with initial state held by this proxy.
         *
         * @return a {@code LongAdder} object with initial state held by this proxy.
         */
        private Object readResolve() {
            LongAdder a = new LongAdder();
            a.base = value;
            return a;
        }
    }

    /**
     * 返回一个 <a href="../../../../serialized-form.html#java.util.concurrent.atomic.LongAdder.SerializationProxy"> SerializationProxy </a> 表示此实例的状态。
     * Returns a <a href="../../../../serialized-form.html#java.util.concurrent.atomic.LongAdder.SerializationProxy"> SerializationProxy </a> representing the state of this instance.
     *
     * @return a {@link SerializationProxy}
     * representing the state of this instance
     */
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    /**
     * @param s the stream
     * @throws java.io.InvalidObjectException always
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.InvalidObjectException {
        throw new java.io.InvalidObjectException("Proxy required");
    }

}
