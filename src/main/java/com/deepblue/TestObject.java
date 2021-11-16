package com.deepblue;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

        HashMap<String, String> map = new HashMap<>();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(true || false);


        String[] source = new String[]{"1111", "2222", "3333", "4444"};
        String[] target = Arrays.copyOf(source, source.length, String[].class);
        System.out.println("target :" + JSON.toJSONString(target));

        System.out.println(Math.pow(2, 16));

        System.out.println(null == null);

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
