package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_006;

public class Count {

	private byte[] lock1 = new byte[1];
	private byte[] lock2 = new byte[1];

	public int num = 0;

	public void add() {
		synchronized (lock1) {
			System.out.println(Thread.currentThread().getName() + " add method invoke begin");

			try {
				Thread.currentThread().sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock2) {
				num += 1;
			}

			System.out.println(Thread.currentThread().getName() + " add method invoke end");
		}
	}

	public void dead() {
		synchronized (lock2) {
			System.out.println(Thread.currentThread().getName() + " dead method invoke begin");

			try {
				Thread.currentThread().sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock1) {
				num += 1;
			}

			System.out.println(Thread.currentThread().getName() + " dead method invoke end");
		}
	}
}
