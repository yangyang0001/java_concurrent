package com.deepblue.inaction.has_error_system_out.chapter_02_safe.thread_004_InnerLock;

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
