package com.deepblue.inaction_03_the_art_of_concurrent_programming.chapter_02_principle.example_001;

public class CodeDemo {

    public static volatile Integer count = 0;

    public synchronized void sayHello0() {
        System.out.println("sayHello0 method invoke");
    }

    public void sayHello1() {
        synchronized ("A") {
            System.out.println("sayHello1 method invoke");
        }
    }

    public synchronized static void sayHello2() {
        System.out.println("sayHello2 method invoke");
    }


    public static void main(String[] args) {

        CodeDemo demo = new CodeDemo();

        demo.sayHello0();
        demo.sayHello1();

        sayHello2();

        System.out.println("count :" + count);
    }




}
