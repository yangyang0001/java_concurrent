package com.deepblue.inaction_02_beauty_of_calculation.chapter_06_lock.example_004;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class NonReentrantLock implements Lock, Serializable {

    public static class Sync extends AbstractQueuedSynchronizer {

        // 判定是否持有排他锁
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }


}
