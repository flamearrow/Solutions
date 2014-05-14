package blockingQueue;

// implement a BlockingQueue that has put(e) and take() method 
//  that blocks when there's no space/no element
// Note 1) wait() and notify() can only be used in synchronized block
//  therefore it's easy to just make the method synchronized
// Note 2) when checking if there's no elements/if it's full and decide to wait() or not
//         we need to use while() as opposed to if() to avoid race condition
//		   in the case where two threads are awakened together, 
//         they will try to increase/decrease the _array together, 
//		   which might cause arrayIndexOutOfBounds
// Note 3) for Generic backArray, use Object[] array, E[] won't be able to initialize
public class MyBlockingQueue<E> {
	private Object[] _array;
	private int _size;
	private int _cnt;

	public MyBlockingQueue(int size) {
		_array = new Object[size];
		_size = size;
		_cnt = 0;
	}

	synchronized void put(E e) throws InterruptedException {
		while (_cnt >= _size) {
			System.out.println("put() blocks");
			wait();
		}
		System.out.println("put() acquires lock, adding object");
		_array[_cnt++] = e;
		System.out.println("now array[" + (_cnt - 1) + "] is "
				+ _array[_cnt - 1]);
		notify();
	}

	@SuppressWarnings("unchecked")
	synchronized E get() throws InterruptedException {
		while (_cnt <= 0) {
			System.out.println("get() blocks");
			wait();
		}
		System.out.println("get() acquires lock, grabing object");
		System.out.println("grabing array[" + (_cnt - 1) + "]");
		E ret = (E) _array[--_cnt];
		notify();
		return ret;
	}

	public static void main(String[] args) throws InterruptedException {
		MyBlockingQueue<Integer> q = new MyBlockingQueue<Integer>(10);
		while (true) {
			for (int i = 0; i < 10; i++)
				new Producer(q).start();
			Thread.sleep(500);
			for (int i = 0; i < 20; i++)
				new Consumer(q).start();
			Thread.sleep(500);
			for (int i = 0; i < 10; i++)
				new Producer(q).start();
			Thread.sleep(500);
			for (int i = 0; i < 1; i++)
				new Consumer(q).start();
		}
	}
}

class Producer extends Thread {
	MyBlockingQueue<Integer> _q;
	static int _cnt = 0;

	public Producer(MyBlockingQueue<Integer> q) {
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

class Consumer extends Thread {
	MyBlockingQueue<Integer> _q;

	public Consumer(MyBlockingQueue<Integer> q) {
		_q = q;
	}

	void consume() {
		try {
			Integer i = _q.get();
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