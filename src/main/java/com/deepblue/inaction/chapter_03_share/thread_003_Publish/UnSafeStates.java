package com.deepblue.inaction.chapter_03_share.thread_003_Publish;

import java.util.HashSet;
import java.util.Set;

public class UnSafeStates {

    private Set<Secret> secretSet;

    private String[] states = new String[] {
        // [AF]非洲, [EU]欧洲, [AS]亚洲, [OA]大洋洲, [NA]北美洲, [SA]南美洲, [AN]南极洲
        "AF", "EU", "AS", "OA", "NA", "SA", "AN"
    };

    public void initialize() {
        secretSet = new HashSet<>();
    }

    public static class Secret {
        // 秘密名称
        private String secretName;
        // 秘密描述
        private String secretDesc;
    }

    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }
}


