package com.deepblue.inaction.system_out_error.chapter_02_safe.thread_003_LazyInit;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {

    private Object object = null;

    public Object getInstance() {
        if(object == null) {
            object = new Object();
        }
        return object;
    }
}
