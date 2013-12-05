package lruCache;

import java.util.HashMap;
import java.util.Map;

// Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
//
// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
// it should invalidate the least recently used item before inserting a new item. 
public class LRUCache {
	/**
	 * Input: 1,[set(2,1),get(2),set(3,2),get(2),get(3)] Output: [1,1,-1]
	 * Expected: [1,-1,2]
	 */
	Node head, tail;
	int capacity;
	int used = 0;
	Map<Integer, Node> backMap;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		backMap = new HashMap<Integer, Node>(capacity);
	}

	public int get(int key) {
		if (backMap.containsKey(key)) {
			liftN(backMap.get(key));
			return backMap.get(key).value;
		} else {
			return -1;
		}
	}

	public void set(int key, int value) {
		if (backMap.containsKey(key)) {
			backMap.get(key).value = value;
			liftN(backMap.get(key));
		} else {
			// need to add one
			Node newNode = new Node(key, value);
			backMap.put(key, newNode);

			if (head != null) {
				newNode.next = head;
				head.previous = newNode;
			} else {
				tail = newNode;
			}
			head = newNode;
			if (used < capacity) {
				used++;
			} else {
				// remove tail
				backMap.remove(tail.key);
				if (head == tail) {
					head = tail = null;
				} else {
					tail = tail.previous;
					tail.next = null;
				}

			}
		}
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.set(2, 1);
		cache.set(1, 1);
		cache.set(2, 3);
		cache.set(4, 1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));

		// for (i = 0; i < cache.capacity * 2; i++) {
		// cache.set(i, i);
		// }
		//
		// for (i = 0; i < cache.capacity * 2; i++) {
		// System.out.println(cache.get(i));
		// }
	}

	private void liftN(Node n) {
		if (n == head)
			return;
		if (n == tail) {
			tail = n.previous;
			tail.next = null;
		} else {
			n.previous.next = n.next;
			if (n.next != null) {
				n.next.previous = n.previous;
			}
		}
		n.previous = null;
		n.next = head;
		head.previous = n;
		head = n;
	}
}

class Node {
	Node next = null, previous = null;
	int value;
	int key;

	public Node(int key, int value) {
		this.key = key;
		this.value = value;
	}
}