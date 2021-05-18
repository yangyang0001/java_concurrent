package com.deepblue.inaction.has_error_system_out.chapter_02_safe.thread_005_Reentrant;

/**
 * 这个东西未来用 HotSpot 分析来查看具体是哪个? 父类的锁 还是 子类的锁
 */
public class Client {
    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }
}
