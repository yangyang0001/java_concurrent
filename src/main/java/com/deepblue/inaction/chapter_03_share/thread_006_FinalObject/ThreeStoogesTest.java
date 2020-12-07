package com.deepblue.inaction.chapter_03_share.thread_006_FinalObject;

import com.alibaba.fastjson.JSON;

public class ThreeStoogesTest {

    public static void main(String[] args) {
        ThreeStooges threeStooges = new ThreeStooges();
        System.out.println("threeStooges :" + JSON.toJSONString(threeStooges));
    }
}
