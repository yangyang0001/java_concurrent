package com.deepblue.inaction.system_out_error.chapter_03_share.thread_002_SyncronizedInteger;

/**
 * 代码清单3.3 保证 value值的 原子性
 */
public class SyncronizedIntegerTest {

    private Integer value;

    public synchronized Integer getValue() {
        return value;
    }

    public synchronized void setValue(Integer value) {
        this.value = value;
    }

}
