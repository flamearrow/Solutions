package readwriteLock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Solution {
}

class Data {
	List<String> _backList;
	ReadWriteLock _lock;
	Lock read, write;

	public Data() {
		_backList = new LinkedList<String>();
		_lock = new ReentrantReadWriteLock();
		read = _lock.readLock();
		write = _lock.writeLock();
	}

	public void read() {
		read.lock();
		try {
			for (String s : _backList) {
				System.out.println(s);
			}
		} finally {
			read.unlock();
		}
	}

	public void write(String[] strs) {
		write.lock();
		try {
			for (String s : strs)
				_backList.add(s);
		} finally {
			write.unlock();
		}
	}
}
