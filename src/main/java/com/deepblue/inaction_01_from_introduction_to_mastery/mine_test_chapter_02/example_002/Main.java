package com.deepblue.inaction_01_from_introduction_to_mastery.mine_test_chapter_02.example_002;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new MineThread(), "MineThread-" + i).start();
        }





    }
}
