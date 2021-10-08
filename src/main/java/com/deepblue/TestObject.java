package com.deepblue;

import lombok.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class TestObject {

    public static void main(String[] args) {

        // 32位 是指 32个字节长度的地址
        double bytes   = Math.pow(2, 32);
        double k_bytes = bytes / 1024;
        double m_bytes = k_bytes / 1024;
        double g_bytes = m_bytes / 1024;
        System.out.println("bytes   = " + bytes  );
        System.out.println("k_bytes = " + k_bytes);
        System.out.println("m_bytes = " + m_bytes);
        System.out.println("g_bytes = " + g_bytes);

    }

    public static void setList(List<Long> sourceList) {
        sourceList.add(1000L);
        sourceList.add(2000L);
    }

    public static void setString(String sourceString) {
        sourceString = "zhangsan";
    }


    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class User {
        private String username;
        private Integer age;
    }

}
