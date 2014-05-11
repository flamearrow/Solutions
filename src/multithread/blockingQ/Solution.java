package multithread.blockingQ;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Solution {
	public static void main(String[] args) {
		Container c = new Container();
		Producer p = new Producer(c);
		Consumer co = new Consumer(c);
		p.start();
		co.start();
	}
}

class Producer extends Thread {
	Container c;
	int base;

	public Producer(Container c) {
		this.c = c;
		base = 0;
	}

	@Override
	public void run() {
		while (true) {
			try {
				c.q.put("mlgb" + base);
				base++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class Container {
	BlockingQueue<String> q;

	public Container() {
		q = new ArrayBlockingQueue<String>(10);
	}
}

class Consumer extends Thread {
	Container c;

	public Consumer(Container c) {
		this.c = c;
	}

	@Override
	public void run() {
		while (true) {
			// String msg = null;
			// while ((msg = c.q.poll()) != null) {
			// System.out.println(msg);
			// }
			while (true) {
				try {
					// q.take() will block automatically, there¡¯s no need to lock
					// q.poll() will return null
					System.out.println(c.q.take());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}