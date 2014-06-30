package multithread.hashMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Implement a rudimental generic hash map
 * Difference btwn jdk map: 
 * jdk map uses a tree to store linked list when a bin grows over 8, 
 *  but here we're always using a linked list
 * note we need to REHASH everything when resizing, 
 *  we can wrap the put(k, v, arrayList) method so that when resize is required we just put each old entry into the new resized entry
 * resize defaults to double the size
 * 
 * Also use a readwrite lock to synchronize it, note we need to wrap lock() and unlock() in a try{} finally{}
 * @author Chen
 *
 */
public class MyHashMap<K, V> {
	private static int DEFAULT_SIZE = 10;
	private MyEntry<K, V>[] _backArray = null;
	private int _occupied = 0;
	private double _loadFactor = 0.75;
	Lock read = null, write = null;
	ReadWriteLock rwl = null;

	public MyHashMap() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public MyHashMap(int size) {
		// convert to MyEntry generic array, warnings need to be suppressed
		_backArray = (MyEntry<K, V>[]) new MyEntry[size];
		rwl = new ReentrantReadWriteLock();
		read = rwl.readLock();
		write = rwl.writeLock();
	}

	public void put(K key, V value) {
		write.lock();
		try {
			boolean sizeIncreased = putToArray(key, value, _backArray);
			int size = _backArray.length;
			if (sizeIncreased && ++_occupied >= size * _loadFactor) {
				resize();
			}
		} finally {
			write.unlock();
		}
	}

	// wrap putToArray() method so that we can put to _backArray and newArray
	private boolean putToArray(K key, V value, MyEntry<K, V>[] array) {
		int size = array.length;
		int index = key.hashCode() % size;
		MyEntry<K, V> cur = array[index];
		boolean sizeIncreased = true;
		if (cur == null) {
			array[index] = new MyEntry<K, V>(key, value);
		} else {
			while (cur.next != null && cur.getKey() != key) {
				cur = cur.next;
			}
			// dup
			if (cur.getKey() == key) {
				cur.setValue(value);
				sizeIncreased = false;
			} else {
				cur.next = new MyEntry<K, V>(key, value);
			}
		}
		return sizeIncreased;
	}

	public V get(K key) {
		read.lock();
		try {
			int size = _backArray.length;
			int index = key.hashCode() % size;
			MyEntry<K, V> cur = _backArray[index];
			while (cur != null && !cur.getKey().equals(key)) {
				cur = cur.next;
			}
			V ret = cur == null ? null : cur.getValue();
			return ret;
		} finally {
			// finally can be used after return
			read.unlock();
		}
	}

	public boolean containsKey(K key) {
		int size = _backArray.length;
		int index = key.hashCode() % size;
		MyEntry<K, V> cur = _backArray[index];
		if (cur == null)
			return false;
		else {
			while (!cur.getKey().equals(key) && cur.next != null) {
				cur = cur.next;
			}
			if (cur.getKey().equals(key))
				return true;
			else
				return false;
		}
	}

	// note we need to rehash each node since the size changed
	private void resize() {
		int size = _backArray.length;
		int newSize = (size << 1);
		// when calling constructor, can't use generic
		@SuppressWarnings("unchecked")
		MyEntry<K, V>[] newBackArray = new MyEntry[newSize];
		// rehash all nodes using existing hash function, note we don't need to change occupied here since we are just changing the size of the _backArray
		for (MyEntry<K, V> entry : _backArray) {
			while (entry != null) {
				putToArray(entry.getKey(), entry.getValue(), newBackArray);
				entry = entry.next;
			}
		}
		_backArray = newBackArray;
	}

	// static class won't be able to interact with outer class, it can only be declared as nested class
	// static class is used when there's no need to interact with outer class and there's no other class other than the outer class need to use it
	static class MyEntry<K, V> {
		K key;
		V value;
		MyEntry<K, V> next = null;

		MyEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		MyHashMap<Integer, String> map = new MyHashMap<Integer, String>(1);
		for (int i = 0; i < 100; i++) {
			map.put(i, "mlgb" + i);
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(map.get(i));
		}
	}
}
