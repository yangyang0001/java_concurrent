package com.deepblue.inaction_02_beauty_of_calculation.chapter_05_copy_on_write.example_001;

import lombok.*;

import java.util.Arrays;

public class ArraysDemo {

	public static void main(String[] args) {

		User user0 = new User("zhangsan", "0000");
		User user1 = new User("lisi", "1111");
		User user2 = new User("wangwu", "2222");
		User user3 = new User("zhaoliu", "3333");

		User[] source = new User[4];
		source[0] = user0;
		source[1] = user1;
		source[2] = user2;
		source[3] = user3;

		User[] target = Arrays.copyOf(source, source.length, User[].class);

		System.out.println(user0 == source[0]);
		System.out.println(user0 == target[0]);
		System.out.println(source[0] == target[0]);

	}

	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class User {
		private String username;
		private String password;
	}
}
