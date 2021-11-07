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
 * 由 Doug Lea 在 JCP JSR-166 专家组成员的协助下撰写并发布到公共领域，如 http://creativecommons.org/publicdomain/zero/1.0/ 所述
 * Written by Doug Lea with assistance from members of JCP JSR-166 Expert Group and released to the public domain, as explained at http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.deepblue.inaction_02_beauty_of_calculation.chapter_04_atomic.example_002;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;
import java.util.function.LongBinaryOperator;

/**
 * 一个包本地类，包含支持 64 位值动态条带化的类的通用表示和机制。
 * A package-local class holding common representation and mechanics for classes supporting dynamic striping on 64bit values.
 *
 * 该类扩展了 Number 以便具体的子类必须公开这样做。
 * The class extends Number so that concrete subclasses must publicly do so.
 */
@SuppressWarnings("serial")
abstract class Striped64 extends Number {
    /**
     * 此类维护一个原子更新变量的延迟初始化表，以及一个额外的 base 字段。 TODO 这句话非常重要: 一个 延迟 List<Cell> + Base
     * This class maintains a lazily-initialized table of atomically updated variables, plus an extra "base" field.
     *
     * 表大小是 2 的幂次倍!
     * The table size is a power of two.
     *
     * 索引使用屏蔽的每线程哈希码。
     * Indexing uses masked per-thread hash codes.
     *
     * 这个类中几乎所有的声明都是包私有的，由子类直接访问。
     * Nearly all declarations in this class are package-private, accessed directly by subclasses.
     *
     * 表格条目属于 Cell 类；填充 AtomicLong 的变体（通过 @sun.misc.Contended）以减少缓存争用。
     * TODO 表明 Cell类 是加上注解 -XX:-RestrictContended 来消除伪共享的
     * Table entries are of class Cell; a variant of AtomicLong padded (via @sun.misc.Contended) to reduce cache contention.
     *
     * 填充对于大多数原子来说是过度的，因为它们通常不规则地分散在内存中，因此不会相互干扰。
     * TODO 对于当个 Cell 而言 加上 -XX:-RestrictContended 是过度设计了, 因为单个 Cell 往往比较分散
     * Padding is overkill for most Atomics because they are usually irregularly scattered in memory and thus don't interfere much with each other.
     *
     * 但是驻留在数组中的原子对象往往会彼此相邻放置，因此在没有这种预防措施的情况下，它们通常会共享缓存行（具有巨大的负面性能影响）。
     * TODO 对于 List<Cell> 而言 每个 Cell 往往都是相邻存放的, 伪共享是优势, 但每个 Cell 是独立的被占用的, 产生竞争时, 这是就是优势了
     * But Atomic objects residing in arrays will tend to be placed adjacent to each other, and so will most often share cache lines (with a huge negative performance impact) without this precaution.
     *
     * 部分是因为 Cell 相对较大，我们避免在需要它们之前创建它们。
     * TODO 因 Cell 相对都较大, 使用之前再创建 采用懒加载创建的方式进行优化
     * In part because Cells are relatively large, we avoid creating them until they are needed.
     *
     * 当没有争用时，所有更新都对基本字段进行。
     * TODO 单线程时, 只使用 base 就够用了
     * When there is no contention, all updates are made to the base field.
     *
     * 在第一次争用时（基本更新失败的 CAS），表被初始化为大小 2。
     * TODO 首次争用时, 对 List<Cell> 初始化, 长度为2
     * Upon first contention (a failed CAS on base update), the table is initialized to size 2.
     *
     * 表大小在进一步争用时加倍，直到达到大于或等于 CPU 数量的最接近的 2 次幂。
     * TODO Cell个数最大为 2 ^ N >= cpu_core_num 此时 N 取最小值!
     * The table size is doubled upon further contention until reaching the nearest power of two greater than or equal to the number of CPUS.
     *
     * 表槽在需要之前保持为空（空）。
     * Table slots remain empty (null) until they are needed.
     *
     * TODO 单个自旋锁（“cellsBusy”）用于初始化和调整表的大小，以及用新单元填充插槽。
     * A single spinlock ("cellsBusy") is used for initializing and resizing the table, as well as populating slots with new Cells.
     *
     * 不需要阻塞锁；当锁不可用时，线程尝试其他插槽（或基）。
     * There is no need for a blocking lock; when the lock is not available, threads try other slots (or the base).
     *
     * 在这些重试期间，争用增加并减少了局部性，这仍然优于替代方案。
     * During these retries, there is increased contention and reduced locality, which is still better than alternatives.
     *
     * 通过 ThreadLocalRandom 维护的线程探测字段用作每个线程的哈希码。
     * The Thread probe fields maintained via ThreadLocalRandom serve as per-thread hash codes.
     *
     * 我们让它们保持未初始化为零（如果它们以这种方式出现），直到它们在插槽 0 处竞争。
     * We let them remain uninitialized as zero (if they come in this way) until they contend at slot 0.
     *
     * 然后将它们初始化为通常不经常与其他值冲突的值。
     * They are then initialized to values that typically do not often conflict with others.
     *
     * 执行更新操作时，失败的 CAS 指示争用和/或表冲突。
     * Contention and/or table collisions are indicated by failed CASes when performing an update operation.
     *
     * 发生冲突时，如果表大小小于容量，则除非其他线程持有锁，否则它的大小将增加一倍。
     * Upon a collision, if the table size is less than the capacity, it is doubled in size unless some other thread holds the lock.
     *
     * 如果哈希槽为空，并且锁可用，则会创建一个新的 Cell。
     * If a hashed slot is empty, and lock is available, a new Cell is created.
     *
     * 否则，如果插槽存在，则尝试 CAS。
     * Otherwise, if the slot exists, a CAS is tried.
     *
     * 重试通过“双重散列”进行，使用二级散列 (Marsaglia XorShift) 尝试找到空闲槽。
     * Retries proceed by "double hashing", using a secondary hash (Marsaglia XorShift) to try to find a free slot.
     *
     * 表大小是有上限的，因为当线程比 CPU 多时，假设每个线程都绑定到一个 CPU，就会存在一个完美的哈希函数将线程映射到插槽，从而消除冲突。
     * The table size is capped because, when there are more threads than CPUs, supposing that each thread were bound to a CPU, there would exist a perfect hash function mapping threads to slots that eliminates collisions.
     *
     * 当我们达到容量时，我们通过随机改变冲突线程的哈希码来搜索这个映射。
     * When we reach capacity, we search for this mapping by randomly varying the hash codes of colliding threads.
     *
     * 因为搜索是随机的，并且冲突只能通过 CAS 故障才能知道，所以收敛速度可能很慢，而且因为线程通常 不会一直 超过 CPU核心数，所以可能根本不会发生。
     * Because search is random, and collisions only become known via CAS failures, convergence can be slow, and because threads are typically not bound to CPUS forever, may not occur at all.
     *
     * 然而，尽管有这些限制，在这些情况下观察到的争用率通常很低。
     * However, despite these limitations, observed contention rates are typically low in these cases.
     *
     * 当曾经散列到它的线程终止时，以及在加倍表导致没有线程在扩展掩码下散列到它的情况下，单元可能变得未使用。
     * It is possible for a Cell to become unused when threads that once hashed to it terminate, as well as in the case where doubling the table causes no thread to hash to it under expanded mask.
     *
     * 我们不会尝试检测或删除此类单元格，假设对于长时间运行的实例，观察到的争用级别将再次发生，因此最终将再次需要这些单元格；而对于短暂的人来说，这无关紧要。
     * We do not try to detect or remove such cells, under the assumption that for long-running instances, observed contention levels will recur, so the cells will eventually be needed again; and for short-lived ones, it does not matter.
     */

    /**
     * 仅支持原始访问和 CAS 的 AtomicLong 的填充变体。
     * Padded variant of AtomicLong supporting only raw accesses plus CAS.
     *
     * JVM 内在函数注意：如果提供了 CAS，则可以在此处使用仅发布形式的 CAS。
     * JVM intrinsics note: It would be possible to use a release-only form of CAS here, if it were provided.
     */
    @sun.misc.Contended static final class Cell {
        volatile long value;
        Cell(long x) { value = x; }
        final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
        }

        // Unsafe mechanics
        private static final sun.misc.Unsafe UNSAFE;
        private static final long valueOffset;
        static {
            try {
                UNSAFE = sun.misc.Unsafe.getUnsafe();
                Class<?> ak = Cell.class;
                valueOffset = UNSAFE.objectFieldOffset
                    (ak.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /**
     * CPUS 数量，用于限制表大小
     * Number of CPUS, to place bound on table size
     */
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    /**
     * 单元格表。 当非空时，size 是 2 的幂。
     * Table of cells. When non-null, size is a power of 2.
     */
    transient volatile Cell[] cells;

    /**
     * base 值，主要在没有争用时使用，但也可作为表初始化竞争期间的后备。 通过 CAS 更新。
     * Base value, used mainly when there is no contention, but also as a fallback during table initialization races. Updated via CAS.
     */
    transient volatile long base;

    /**
     * 调整大小和/或创建单元格时使用的自旋锁（通过 CAS 锁定）。
     * Spinlock (locked via CAS) used when resizing and/or creating Cells.
     */
    transient volatile int cellsBusy;

    /**
     * 包私有默认构造函数
     * Package-private default constructor
     */
    Striped64() {
    }

    /**
     * CASes 基本字段。
     * CASes the base field.
     */
    final boolean casBase(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, BASE, cmp, val);
    }

    /**
     * CASes cellBusy 字段从 0 到 1 以获取锁。
     * CASes the cellsBusy field from 0 to 1 to acquire lock.
     */
    final boolean casCellsBusy() {
        return UNSAFE.compareAndSwapInt(this, CELLSBUSY, 0, 1);
    }

    /**
     * 返回当前线程的探测值。
     * Returns the probe value for the current thread.
     *
     * 由于包装限制，从 ThreadLocalRandom 复制。
     * Duplicated from ThreadLocalRandom because of packaging restrictions.
     */
    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }

    /**
     * 伪随机前进并记录给定线程的给定探测值。
     * Pseudo-randomly advances and records the given probe value for the given thread.
     *
     * 由于包装限制，从 ThreadLocalRandom 复制。
     * Duplicated from ThreadLocalRandom because of packaging restrictions.
     */
    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
        return probe;
    }

    /**
     * 处理涉及初始化、调整大小、创建新单元和/或争用的更新情况。
     * Handles cases of updates involving initialization, resizing, creating new Cells, and/or contention.
     *
     * 见上文解释。
     * See above for explanation.
     *
     * 这种方法会遇到乐观重试代码的常见非模块化问题，依赖于重新检查的读取集。
     * This method suffers the usual non-modularity problems of optimistic retry code, relying on rechecked sets of reads.
     *
     *          值
     * @param x the value
     *           更新函数，或 null 用于添加（此约定避免了 LongAdder 中额外字段或函数的需要）。
     * @param fn the update function, or null for add (this convention avoids the need for an extra field or function in LongAdder).
     *                       如果 CAS 在调用前失败，则为 false
     * @param wasUncontended false if CAS failed before call
     */
    final void longAccumulate(long x, LongBinaryOperator fn,
                              boolean wasUncontended) {
        int h;
        if ((h = getProbe()) == 0) {
            ThreadLocalRandom.current(); // force initialization
            h = getProbe();
            wasUncontended = true;
        }
        boolean collide = false;                // True if last slot nonempty
        for (;;) {
            Cell[] as; Cell a; int n; long v;
            if ((as = cells) != null && (n = as.length) > 0) {
                if ((a = as[(n - 1) & h]) == null) {
                    if (cellsBusy == 0) {       // Try to attach new Cell
                        Cell r = new Cell(x);   // Optimistically create
                        if (cellsBusy == 0 && casCellsBusy()) {
                            boolean created = false;
                            try {               // Recheck under lock
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null &&
                                    (m = rs.length) > 0 &&
                                    rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created)
                                break;
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = false;
                }
                else if (!wasUncontended)       // CAS already known to fail
                    wasUncontended = true;      // Continue after rehash
                else if (a.cas(v = a.value, ((fn == null) ? v + x :
                                             fn.applyAsLong(v, x))))
                    break;
                else if (n >= NCPU || cells != as)
                    collide = false;            // At max size or stale
                else if (!collide)
                    collide = true;
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        if (cells == as) {      // Expand table unless stale
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i)
                                rs[i] = as[i];
                            cells = rs;
                        }
                    } finally {
                        cellsBusy = 0;
                    }
                    collide = false;
                    continue;                   // Retry with expanded table
                }
                h = advanceProbe(h);
            }
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    if (cells == as) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(x);
                        cells = rs;
                        init = true;
                    }
                } finally {
                    cellsBusy = 0;
                }
                if (init)
                    break;
            }
            else if (casBase(v = base, ((fn == null) ? v + x :
                                        fn.applyAsLong(v, x))))
                break;                          // Fall back on using base
        }
    }

    /**
     * 与 longAccumulate 相同，但考虑到此类的低开销要求，在太多地方注入了 long/double 转换以与 long 版本明智地合并。
     * Same as longAccumulate, but injecting long/double conversions in too many places to sensibly merge with long version, given the low-overhead requirements of this class.
     *
     * 所以必须通过复制/粘贴/适应来维护。
     * So must instead be maintained by copy/paste/adapt.
     */
    final void doubleAccumulate(double x, DoubleBinaryOperator fn,
                                boolean wasUncontended) {
        int h;
        if ((h = getProbe()) == 0) {
            ThreadLocalRandom.current(); // force initialization
            h = getProbe();
            wasUncontended = true;
        }
        boolean collide = false;                // True if last slot nonempty
        for (;;) {
            Cell[] as; Cell a; int n; long v;
            if ((as = cells) != null && (n = as.length) > 0) {
                if ((a = as[(n - 1) & h]) == null) {
                    if (cellsBusy == 0) {       // Try to attach new Cell
                        Cell r = new Cell(Double.doubleToRawLongBits(x));
                        if (cellsBusy == 0 && casCellsBusy()) {
                            boolean created = false;
                            try {               // Recheck under lock
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null &&
                                    (m = rs.length) > 0 &&
                                    rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created)
                                break;
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = false;
                }
                else if (!wasUncontended)       // CAS already known to fail
                    wasUncontended = true;      // Continue after rehash
                else if (a.cas(v = a.value,
                               ((fn == null) ?
                                Double.doubleToRawLongBits
                                (Double.longBitsToDouble(v) + x) :
                                Double.doubleToRawLongBits
                                (fn.applyAsDouble
                                 (Double.longBitsToDouble(v), x)))))
                    break;
                else if (n >= NCPU || cells != as)
                    collide = false;            // At max size or stale
                else if (!collide)
                    collide = true;
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        if (cells == as) {      // Expand table unless stale
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i)
                                rs[i] = as[i];
                            cells = rs;
                        }
                    } finally {
                        cellsBusy = 0;
                    }
                    collide = false;
                    continue;                   // Retry with expanded table
                }
                h = advanceProbe(h);
            }
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    if (cells == as) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(Double.doubleToRawLongBits(x));
                        cells = rs;
                        init = true;
                    }
                } finally {
                    cellsBusy = 0;
                }
                if (init)
                    break;
            }
            else if (casBase(v = base,
                             ((fn == null) ?
                              Double.doubleToRawLongBits
                              (Double.longBitsToDouble(v) + x) :
                              Double.doubleToRawLongBits
                              (fn.applyAsDouble
                               (Double.longBitsToDouble(v), x)))))
                break;                          // Fall back on using base
        }
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long BASE;
    private static final long CELLSBUSY;
    private static final long PROBE;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> sk = Striped64.class;
            BASE = UNSAFE.objectFieldOffset
                (sk.getDeclaredField("base"));
            CELLSBUSY = UNSAFE.objectFieldOffset
                (sk.getDeclaredField("cellsBusy"));
            Class<?> tk = Thread.class;
            PROBE = UNSAFE.objectFieldOffset
                (tk.getDeclaredField("threadLocalRandomProbe"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
