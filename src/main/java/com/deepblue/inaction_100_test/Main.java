package com.deepblue.inaction_100_test;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object1 = new MineObject();
        MineObject object2 = new MineObject();

        MineThread threadA = new MineThread(object1);
        threadA.start();

        MineThread threadB = new MineThread(object2);
        threadB.start();

    }
}
