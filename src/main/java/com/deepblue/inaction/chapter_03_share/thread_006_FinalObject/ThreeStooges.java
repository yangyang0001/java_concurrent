package com.deepblue.inaction.chapter_03_share.thread_006_FinalObject;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * 基于可变对象 创建的 不可变对象
 * 可变对象: Set<String>
 *          NoFinalObject
 */
public class ThreeStooges {

    public final Set<String> threeStooges = new HashSet<>();

    public final NoFinalObject noFinalObject = new NoFinalObject("mineName", "minePass");

    public ThreeStooges() {
        System.out.println("construct start");
        threeStooges.add("张三");
        threeStooges.add("李四");
        threeStooges.add("王五");
        System.out.println("threeStooges :" + JSON.toJSONString(threeStooges));
        System.out.println("construct end");

    }

    public boolean isStooge(String name) {
        return threeStooges.contains(name);
    }
}
