
package com.deepblue.inaction.system_out_error.chapter_03_share.thread_001_NoVisible;

public class NoVisibleTest {

    public static Integer number = 100;
    public static Boolean breakFlag = false;

    public static class NoVisibleThread extends Thread {
        @Override
        public void run() {
            while(true) {
                if(breakFlag) {
                    // 线程让步的意思, 这里的线程让步是有其他线程同时执行的情况下, 才会起到作用, 否则独占线程, 可以换成 break
                    // Thread.yield();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        NoVisibleThread thread_0001 = new NoVisibleThread();
        thread_0001.setName("thread_0001");
        NoVisibleThread thread_0002 = new NoVisibleThread();
        thread_0002.setName("thread_0002");

        thread_0001.start();
        thread_0002.start();

        Thread.currentThread().sleep(3000L);
        number = 666;
        breakFlag = true;
        System.out.println("threadName is " + Thread.currentThread().getName() + ", number is " + number + ", breakFlag is " + breakFlag);
    }

    public static void invokedPrint(String location, String name, Integer number, Boolean breakFlag) {
        System.out.println(location + ", threadName is " + name + ", number is " + number + ", breakFlag is " + breakFlag);
    }
}

