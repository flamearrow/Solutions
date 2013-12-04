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
	Node useHead;
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
			liftN(backMap.get(key));
		} else {
			// need to add one
			Node newNode = new Node(key, value);
			backMap.put(key, newNode);

			if (useHead != null) {
				newNode.useNext = useHead;
				useHead.usePrevious = newNode;
			}
			useHead = newNode;
			if (used < capacity) {
				used++;
			} else {
				Node toRemove = backMap.remove(key);
				// need to remove it from uselist
				if (toRemove == useHead) {
					useHead = toRemove.useNext;
					useHead.usePrevious = null;
				} else {
					toRemove.usePrevious.useNext = toRemove.useNext;
					if (toRemove.useNext != null)
						toRemove.useNext.usePrevious = toRemove.usePrevious;
				}
			}
		}
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);
		cache.set(10, 13);
		cache.set(3, 17);
		cache.set(6, 11);
		cache.set(10, 5);
		cache.set(9, 10);
		cache.get(13);
		cache.set(2, 19);
		cache.get(2);
		cache.get(3);
		cache.set(5, 25);
		cache.get(8);
		cache.set(9, 22);
		cache.set(5, 5);
		cache.set(1, 30);
		cache.get(11);
		cache.set(9, 12);
		cache.get(7);
		cache.get(5);
		cache.get(8);
		cache.get(9);
		cache.set(4, 30);
		cache.set(9, 3);
		cache.get(9);
		cache.get(10);
		cache.get(10);
		cache.set(6, 14);
		cache.set(3, 1);
		cache.get(3);
		cache.set(10, 11);
		cache.get(8);
		cache.set(2, 14);
		cache.get(1);
		cache.get(5);
		cache.get(4);
		cache.set(11, 4);
		cache.set(12, 24);
		cache.set(5, 18);
		cache.get(13);
		cache.set(7, 23);
		cache.get(8);
		cache.get(12);
		cache.set(3, 27);
		cache.set(2, 12);
		cache.get(5);
		cache.set(2, 9);
		cache.set(13, 4);
		cache.set(8, 18);
		cache.set(1, 7);
		cache.get(6);
		cache.set(9, 29);
		cache.set(8, 21);
		cache.get(5);
		cache.set(6, 30);
		cache.set(1, 12);
		cache.get(10);
		cache.set(4, 15);
		cache.set(7, 22);
		cache.set(11, 26);
		cache.set(8, 17);
		cache.set(9, 29);
		cache.get(5);
		cache.set(3, 4);
		cache.set(11, 30);
		cache.get(12);
		cache.set(4, 29);
		cache.get(3);
		cache.get(9);
		cache.get(6);
		cache.set(3, 4);
		cache.get(1);
		cache.get(10);
		cache.set(3, 29);
		cache.set(10, 28);
		cache.set(1, 20);
		cache.set(11, 13);
		cache.get(3);
		cache.set(3, 12);
		cache.set(3, 8);
		cache.set(10, 9);
		cache.set(3, 26);
		cache.get(8);
		cache.get(7);
		cache.get(5);
		cache.set(13, 17);
		cache.set(2, 27);
		cache.set(11, 15);
		cache.get(12);
		cache.set(9, 19);
		cache.set(2, 15);
		cache.set(3, 16);
		cache.get(1);
		cache.set(12, 17);
		cache.set(9, 1);
		cache.set(6, 19);
		cache.get(4);
		cache.get(5);
		cache.get(5);
		cache.set(8, 1);
		cache.set(11, 7);
		cache.set(5, 2);
		cache.set(9, 28);
		cache.get(1);
		cache.set(2, 2);
		cache.set(7, 4);
		cache.set(4, 22);
		cache.set(7, 24);
		cache.set(9, 26);
		cache.set(13, 28);
		cache.set(11, 26);
		// for (i = 0; i < cache.capacity * 2; i++) {
		// cache.set(i, i);
		// }
		//
		// for (i = 0; i < cache.capacity * 2; i++) {
		// System.out.println(cache.get(i));
		// }
	}

	private void liftN(Node n) {
		if (n == useHead)
			return;
		n.usePrevious.useNext = n.useNext;
		if (n.useNext != null) {
			n.useNext.usePrevious = n.usePrevious;
		}
		n.usePrevious = null;
		n.useNext = useHead;
		useHead.usePrevious = n;
		useHead = n;
	}
}

class Node {
	Node useNext = null, usePrevious = null;
	int value;
	int key;

	public Node(int key, int value) {
		this.key = key;
		this.value = value;
	}
}