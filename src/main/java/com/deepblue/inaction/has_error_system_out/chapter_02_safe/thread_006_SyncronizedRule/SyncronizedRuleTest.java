package com.deepblue.inaction.has_error_system_out.chapter_02_safe.thread_006_SyncronizedRule;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用管理类状态的方式, 用当前类中的内部锁来管理整个类中的所有可变状态字段
 */
public class SyncronizedRuleTest {

    public static void main(String[] args) {
        Vector vector = null;
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
    }
}
