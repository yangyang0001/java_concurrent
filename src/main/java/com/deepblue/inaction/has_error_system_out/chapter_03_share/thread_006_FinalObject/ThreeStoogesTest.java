package com.deepblue.inaction.has_error_system_out.chapter_03_share.thread_006_FinalObject;

import com.alibaba.fastjson.JSON;

public class ThreeStoogesTest {

    public static void main(String[] args) {
        ThreeStooges threeStooges = new ThreeStooges();
        System.out.println("inner hash :" + threeStooges.threeStooges.hashCode());
        threeStooges.threeStooges.add("赵六");
        System.out.println("outer hash :" + threeStooges.threeStooges.hashCode());
        System.out.println("threeStooges :" + JSON.toJSONString(threeStooges));
    }
}
