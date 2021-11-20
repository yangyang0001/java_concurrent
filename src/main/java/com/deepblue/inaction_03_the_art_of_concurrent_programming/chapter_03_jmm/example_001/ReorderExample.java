package com.deepblue.inaction_03_the_art_of_concurrent_programming.chapter_03_jmm.example_001;

/**
 * 重排序解释 书中的例子
 */
public class ReorderExample {

    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1;          // 1
        flag = true;    // 2
    }

    public void reader() {

        if(flag) {          // 3
            int i = a * a;  // 4
        }

    }

}
