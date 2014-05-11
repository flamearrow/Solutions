package multiplyStrings.regularQ;

import java.util.LinkedList;
import java.util.Queue;

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
			synchronized (c.q) {
				c.q.offer("mlgb" + base);
				base++;
				c.q.notifyAll();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class Container {
	Queue<String> q;

	public Container() {
		q = new LinkedList<String>();
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
			synchronized (c.q) {
				if (c.q.isEmpty()) {
					try {
						c.q.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println(c.q.poll());
				}
			}
		}
	}
}