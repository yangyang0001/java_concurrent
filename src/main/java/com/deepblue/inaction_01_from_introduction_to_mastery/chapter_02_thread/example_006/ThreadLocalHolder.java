package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_02_thread.example_006;

import java.util.Map;

public class ThreadLocalHolder {

    public static ThreadLocal<Integer> localA = new ThreadLocal<>();

    public static Integer getNextNum() {
        localA.set((localA.get() == null ? 0 : localA.get()) + 1);
        return localA.get();
    }

    public static void remove() {
        localA.remove();
    }

}
