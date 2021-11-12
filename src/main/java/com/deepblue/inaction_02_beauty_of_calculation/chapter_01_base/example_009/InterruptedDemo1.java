package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_009;

/**
 * 验证 interrupt() 方法只会改变标识, 极有可能不会中断线程!
 */
public class InterruptedDemo1 {

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA begin interrupt!");

                Thread.currentThread().interrupt();

                System.out.println("threadA  end  interrupt!");
            }
        });

        threadA.start();

    }
}
