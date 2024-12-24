package com.deepblue.inaction_100_test;

import lombok.*;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MineThread extends Thread{

    private MineObject mineObject;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("current thread id = " + Thread.currentThread().getId() + ", run method invoke!");
        mineObject.count();
    }
}
