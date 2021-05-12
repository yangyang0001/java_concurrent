import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestObject {
    public static void main(String[] args) {

        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(100);
        list.add(10);
        list.add(20);
        list.add(400);
        list.add(30);
        list.add(500);
        list.stream().forEach(System.out::println);

        // 降序排列
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer event1, Integer event2) {
                int flag = event2.compareTo(event1);
//                System.out.println("event1 is: " + event1 + ", event2 is :" + event2 + ", flag is :" + flag);
                return flag;
            }
        });
        System.out.println("---------------------------------------------------------------------------------");
        list.stream().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------");

        List<Person> personList = Lists.newArrayList();
        personList.add(new Person("zhangsan1111", "123456", 1, 20));
        personList.add(new Person("lisi11111111", "123456", 0, 20));
        personList.add(new Person("wangwu", "123456", 1, 21));
        personList.add(new Person("zhaoliu", "123456", 0, 22));

        Map<Integer, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(person -> person.getGender()));

        for (Map.Entry<Integer, List<Person>> entry : collect.entrySet()) {
            System.out.println("key   :" + entry.getKey());
            List<Person> value = entry.getValue();
            // 升序排列
            Collections.sort(value, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getAge().compareTo(o2.getAge());
                }
            });
            System.out.println("value :" + JSON.toJSONString(value));
            System.out.println("---------------------------------------------------------------------------------");
        }

        Map<Integer, Person> collect1 = personList.stream().collect(Collectors.toMap(item -> item.getGender(), item -> item, (key1, key2) -> key1));
        System.out.println("collect1 :" + JSON.toJSONString(collect1));

    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Person {
        private String userName;
        private String password;
        private Integer gender;
        private Integer age;
    }
}
