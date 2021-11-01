package com.deepblue.inaction_02_beauty_of_calculation.chapter_01_base.example_011;

public class DaemonThreadDemo {

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				for( ; ; ) {}
			}
		});

		// start() 方法执行前, 设置 daemon 就将当前线程 置为了 守护线程了
		threadA.setDaemon(true);

		threadA.start();

		System.out.println("thread main method invoke end!");
	}
}
