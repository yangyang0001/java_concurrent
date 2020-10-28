package com.deepblue.inaction.thread_003_LazyInit;

import lombok.*;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SafeSingleton {

    public static User getInstance() {
        return UserHolder.getInstance();
    }

    public static class UserHolder {
        public static User instance = null;

        static {
            System.out.println("static block invoke -----------------");
            instance = new User("Yang", "123456");
        }

        public static User getInstance() {
            return instance;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String username;
        private String password;
    }
}
