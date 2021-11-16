package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_007;

import java.util.concurrent.locks.StampedLock;

/**
 * jdk8中的 新的锁!
 * 提供了三种模式的 读写 控制!
 *      获取锁的方法 为 try 系列函数
 *      返回值为 long 类型的 如果返回 0 则表示获取锁失败! 返回 > 0 的 stamp 表示获取锁成功!
 *
 * TODO 特别注意 StampedLock 提供的 独占写锁
 *                                悲观读锁
 *                                乐观读锁
 *
 * TODO StampedLock 这三种模式的锁 都是不可重入锁, 这一点非常重要! 这种锁 不推荐使用!
 */
public class StampedLockDemo {

    public static StampedLock lock = new StampedLock();

    public static void main(String[] args) {

        // 获取写锁
        long stamp0 = lock.writeLock();
        // 释放写锁
        lock.unlockWrite(stamp0);

        // 获取 悲观读锁
        long stamp1 = lock.readLock();
        // 释放 悲观读锁
        lock.unlockRead(stamp1);

        // 获取 乐观读锁
        long stamp2 = lock.tryOptimisticRead();
        // 释放 乐观读锁
        lock.unlockRead(stamp2);

    }
}
