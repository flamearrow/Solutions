package crackingTheCodingInterview.executeInSeq;

import java.util.concurrent.Semaphore;

// Use semaphore to make three threads calls method in sequence
public class Solution {
	public static void main(String[] args) throws InterruptedException {
		new Caller().doCall();
	}
}

class Foo {
	Semaphore s1, s2, s3;

	public Foo() {
		s1 = new Semaphore(1);
		s2 = new Semaphore(0);
		s3 = new Semaphore(0);
	}

	public void first() throws InterruptedException {
		s1.acquire();
		System.out.println("first");
		s2.release();
	}

	public void second() throws InterruptedException {
		s2.acquire();
		System.out.println("second");
		s3.release();
	}

	public void third() throws InterruptedException {
		s3.acquire();
		System.out.println("third");
		s1.release();
	}
}

class Caller {
	Foo f;

	public Caller() {
		f = new Foo();
	}

	public void doCall() throws InterruptedException {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						f.first();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						f.second();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						f.third();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
