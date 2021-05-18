package com.deepblue.inaction;

import lombok.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class TestObject {

    public static void main(String[] args) {
//        System.out.println("Hello Concurrent");

//        List<Integer> list = Lists.newArrayList();
//        list.add(100);
//        list.add(101);
//        list.add(102);

//        list.stream().forEach(System.out::println);
//
//        AtomicLong count = new AtomicLong(0L);
//        for(int i = 0; i < 100; i++) {
//            long counting = count.incrementAndGet();
//            System.out.println(counting);
//        }
//
//        BigDecimal aa = BigDecimal.valueOf(0).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//        System.out.println(aa);
//
//
//        List<Long> sourceList = new ArrayList<>();
//        setList(sourceList);
//        sourceList.stream().forEach(System.out::println);
//
//        String sourceString = null;
//        setString(sourceString);
//        System.out.println(sourceString);

//        int topNo = 0;
//        List<Integer> subList = list.subList(0, topNo);
//        System.out.println("subList :" + JSON.toJSONString(subList));
//
//        topNo = 100;
//        subList = list.subList(0, Math.min(topNo, list.size()));
//        System.out.println("subList :" + JSON.toJSONString(subList));
//
//        List<User> userList = Lists.newArrayList();
//        User user0 = new User("zhangsan", 28);
//        User user1 = new User("lisi", 33);
//        User user2 = new User("wangwu", 10);
//        userList.add(user0);
//        userList.add(user1);
//        userList.add(user2);
//
//        Collections.sort(userList, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                if (o1.getAge() > o2.getAge()) {
//                    return -1;
//                } else if (o1.getAge() < o2.getAge()) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        });
//
//        System.out.println("userList :" + JSON.toJSONString(userList));

//        String bb = null;
//        System.out.println("bb.toString    :" + bb.toString());
//        System.out.println("String.valueOf :" + String.valueOf(bb));

//        double rewards = 12.45d;
//        rewards = 12.45d;
//        rewards = 12.45d;
//        String format = new DecimalFormat("").format(rewards);
//        System.out.println("format  :" + format);
//
        String value = "12.45";
        String format1 = new DecimalFormat("#.##").format(Double.valueOf(value));
        System.out.println("format1 :" + format1);

//        String aaa = "0.9664";
//        System.out.println(BigDecimal.valueOf(Double.valueOf(aaa)));
//
//        Integer qualificationType = 0;
//        double settlementRatio = 0;
//        if (qualificationType != null) {
//            if (qualificationType == 0) {
//                settlementRatio = 0.0000d;
//            } else if (qualificationType == 1) {
//                settlementRatio = 1.0000d;
//            } else if (qualificationType == 2) {
//                settlementRatio = 0.9664d;
//            }
//        }
//        System.out.println(BigDecimal.valueOf(settlementRatio).setScale(4));

        BigDecimal totalRewardsBD = BigDecimal.valueOf(10.00).setScale(2);
        BigDecimal settlementRatioBD = BigDecimal.valueOf(0.9664).setScale(4);

        BigDecimal bigDecimal = totalRewardsBD.multiply(settlementRatioBD).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("bigDecimal :" + bigDecimal);

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
