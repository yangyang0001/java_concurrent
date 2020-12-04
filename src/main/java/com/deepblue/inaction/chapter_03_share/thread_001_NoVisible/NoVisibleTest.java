
package com.deepblue.inaction.chapter_03_share.thread_001_NoVisible;

public class NoVisibleTest {

    public static Integer number = 100;
    public static Boolean breakFlag = false;

    public static class NoVisibleThread extends Thread {
        @Override
        public void run() {
            while(true) {
                System.out.println("outside if number :" + number + ", breakFlag :" + breakFlag);
                if(breakFlag) {
                    System.out.println("inside if number :" + number + ", breakFlag :" + breakFlag);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        NoVisibleThread thread = new NoVisibleThread();
        thread.start();

        Thread.currentThread().sleep(1000L);
        number = 666;
        breakFlag = true;
    }
}

