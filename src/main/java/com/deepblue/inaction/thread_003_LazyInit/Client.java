package com.deepblue.inaction.thread_003_LazyInit;

public class Client {

    public static void main(String[] args) {

        SafeSingleton.User user1 = SafeSingleton.getInstance();
        SafeSingleton.User user2 = SafeSingleton.getInstance();

        System.out.println(user1 == user2);


    }
}
