package multithread.h20;

import java.util.concurrent.Semaphore;

// implement h() and o() such that all initial calls will be block until
//  there're two threads blocking on h() and one thread blocking o()
//  in such case return these three threads
public class Solution {
	public static void main(String[] args) {
		H2O h = new H2O();
		new HCaller(h).start();
		new OCaller(h).start();
		new HCaller(h).start();
		new HCaller(h).start();
		new OCaller(h).start();
		new HCaller(h).start();
		new HCaller(h).start();
		new OCaller(h).start();
		new HCaller(h).start();
		new OCaller(h).start();
		new HCaller(h).start();
	}
}

class HCaller extends Thread {
	private H2O h;

	public HCaller(H2O h) {
		this.h = h;
	}

	@Override
	public void run() {
		h.h();
	}
}

class OCaller extends Thread {
	private H2O h;

	public OCaller(H2O h) {
		this.h = h;
	}

	@Override
	public void run() {
		h.o();
	}
}

class H2O {
	Semaphore sh, so;
	int cnt;

	public H2O() {
		sh = new Semaphore(0);
		so = new Semaphore(0);
		cnt = 0;
	}

	public void h() {
		so.release(1);
		try {
			sh.acquire(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("h() returns");
	}

	public void o() {
		try {
			so.acquire(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.release(2);
		System.out.println("o() returns, " + ++cnt + " h20 is created");
	}
}