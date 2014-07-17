package lruCache;

import java.util.HashMap;
import java.util.Map;

//Design and implement a data structure for Least Recently Used (LRU) cache. 
// It should support the following operations: get and set.
//
//get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
//it should invalidate the least recently used item before inserting a new item.
public class LRUCache3 {
	
	// lift to head when 1) get() and 2) set() an existing value
	// the internal node should have both key and value as key is required to remove from backmap
	public static void main(String[] args) {
		LRUCache3 cache = new LRUCache3(2);
		cache.set(2, 1);
		cache.set(1, 1);
		cache.set(2, 3);
		cache.set(4, 1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
	}

	Map<Integer, CacheNode> _backMap;
	CacheNode _head, _tail;
	int _count, _capacity;

	public LRUCache3(int capacity) {
		_backMap = new HashMap<Integer, CacheNode>(capacity);
		_head = null;
		_tail = null;
		_count = 0;
		_capacity = capacity;
	}

	// update recently used during get()
	public int get(int key) {
		if (_backMap.containsKey(key)) {
			lift(_backMap.get(key));
			return _backMap.get(key).val;
		} else {
			return -1;
		}
	}

	public void set(int key, int value) {
		if (_backMap.containsKey(key)) {
			_backMap.get(key).val = value;
			lift(_backMap.get(key));
		} else {
			CacheNode n = new CacheNode(key, value);
			_backMap.put(key, n);
			if (_count == 0) {
				_head = n;
				_tail = n;
			} else {
				n.next = _head;
				_head.prev = n;
				_head = n;
			}
			// need to remove tail AND remvoe the entry from map
			if ((++_count) > _capacity) {
				_tail = _tail.prev;
				_backMap.remove(_tail.next.key);
				_tail.next = null;
			}
		}
	}

	void lift(CacheNode n) {
		// if it's head then we don't do anything
		if (n != _head) {
			if (n == _tail) {
				// cut tail
				_tail = _tail.prev;
				_tail.next = null;
			} else {
				// cut n from it's previous node
				n.prev.next = n.next;
				n.next.prev = n.prev;
			}
			n.prev = null;
			n.next = _head;
			_head.prev = n;
			_head = n;
		}
	}

	private static class CacheNode {
		CacheNode next, prev;
		int key, val;

		CacheNode(int argKey, int argVal) {
			key = argKey;
			val = argVal;
		}
	}

	public void printList() {
		CacheNode cur = _head;
		while (cur != null) {
			System.out.println("key:" + cur.key + ", value:" + cur.val);
			cur = cur.next;
		}
	}
}
