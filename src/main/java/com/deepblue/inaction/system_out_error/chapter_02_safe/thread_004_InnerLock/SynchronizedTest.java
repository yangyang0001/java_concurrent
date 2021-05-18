package com.deepblue.inaction.system_out_error.chapter_02_safe.thread_004_InnerLock;

public class SynchronizedTest {

    private Long count = 0L;

    public synchronized Long getCounting() {
        return count++;
    }

    public Long getCountingV2() {
        synchronized (this) {
            return count++;
        }
    }

}
