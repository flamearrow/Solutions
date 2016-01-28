package multithread.blockingQ;


public class MyBlockingQueue<E> {
	// for Generic types, use Object[] as back array, cast to E when return
	Object[] _arr;
	int _capacity;
	int _head, _tail;
	boolean _full;

	public MyBlockingQueue(int capacity) {
		_arr = new Object[capacity];
		_capacity = capacity;
		_head = 0;
		_tail = 0;
		_full = false;
	}

	public synchronized void put(E e) throws InterruptedException {
		while (_full) {
			System.out.println("Queue is FULL, waiting for more spaces...");
			wait();
		}
		System.out.println("Queue has more spaces, putting '" + e + "' to "
				+ _tail);
		_arr[_tail] = e;
		_tail = (_tail + 1) % _capacity;
		if (_tail == _head) {
			System.out.println("Queue becomes full!");
			_full = true;
		}
		notify();
	}

	@SuppressWarnings("unchecked")
	public synchronized E take() throws InterruptedException {
		// empty
		while (_head == _tail && !_full) {
			System.out.println("Queue is EMPTY, waiting for more occupants...");
			wait();
		}
		E ret = (E) _arr[_head];
		System.out.println("Queue has more occupants, getting '" + ret
				+ "' from " + _head);
		_head = (_head + 1) % _capacity;
		if (_head == _tail) {
			System.out.println("Queue becomes empty!");
			_full = false;
		}
		notify();
		return ret;
	}

	public static void main(String[] args) throws InterruptedException {
		MyBlockingQueue<Integer> q = new MyBlockingQueue<Integer>(10);
		for (int i = 0; i < 10; i++)
			new QueueProducer(q).start();
		Thread.sleep(500);
		for (int i = 0; i < 20; i++)
			new QueueConsumer(q).start();
		Thread.sleep(500);
		for (int i = 0; i < 10; i++)
			new QueueProducer(q).start();
//		Thread.sleep(500);
//		for (int i = 0; i < 1; i++)
//			new QueueConsumer(q).start();
	}
}

class QueueProducer extends Thread {
	MyBlockingQueue<Integer> _q;
	static int _cnt = 0;

	public QueueProducer(MyBlockingQueue<Integer> q) {
		_q = q;
	}

	void produce() {
		try {
			_q.put(_cnt++);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("procue() is interrupted");
		}
	}

	@Override
	public void run() {
		produce();
	}
}

class QueueConsumer extends Thread {
	MyBlockingQueue<Integer> _q;

	public QueueConsumer(MyBlockingQueue<Integer> q) {
		_q = q;
	}

	void consume() {
		try {
			Integer i = _q.take();
			System.out.println(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("consume() is interrupted");
		}
	}

	@Override
	public void run() {
		consume();
	}
}
