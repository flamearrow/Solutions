package lruCache;

import java.util.HashMap;
import java.util.Map;

// Design and implement a data structure for Least Recently Used (LRU) cache. 
// It should support the following operations: get and set. 
// a node will be pulled to head by either get() or set()

// a few notes:
//  0) basic methods to implement: addToHead(), liftToHead() and removeTail()
//  1) LRUNode should contain both Key and Value, because when we remove tail, 
//       we need to know which key is this node mapped to and remove the key
//  2) tail should be updated in addTohead - when we're adding the second node, we need to set tail to previous head
//  3) since we have both prev and next to achieve O(1) operation, need to take care updaing both ptr
public class LRUCache2 {
	Map<Integer, LRUNode> _m = null;
	int capacity = 0;;
	LRUNode head = null, tail = null;

	public LRUCache2(int capacity) {
		_m = new HashMap<Integer, LRUNode>(capacity);
		this.capacity = capacity;
	}

	// Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	public int get(int key) {
		if (_m.containsKey(key)) {
			liftToHead(_m.get(key));
			return _m.get(key).val;
		} else
			return -1;
	}

	// Set or insert the value if the key is not already present. 
	// When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item. 
	public void set(int key, int value) {
		if (_m.containsKey(key)) {
			LRUNode node = _m.get(key);
			node.val = value;
			liftToHead(node);
		} else {
			LRUNode node = new LRUNode(key, value);
			_m.put(key, node);
			addToHead(node);
			// need to remove tail
			if (_m.size() > capacity) {
				removeTail();
			}
		}
	}

	private void removeTail() {
		if (tail != null) {
			int key = tail.key;
			tail = tail.prev;
			tail.next = null;
			_m.remove(key);
		}
	}

	private void addToHead(LRUNode node) {
		node.next = head;
		head = node;
		if (head.next != null)
			head.next.prev = head;
		if (tail == null)
			tail = head;
		else {
			// note tail will only be updated when we grow from one node to two nodes
			if (tail.prev == null)
				tail.prev = head;
		}
	}

	private void liftToHead(LRUNode node) {
		if (node != head) {
			LRUNode n = node.next;
			LRUNode p = node.prev;

			// n will be null
			if (node == tail) {
				tail = p;
				p.next = null;
			} else {
				p.next = n;
				n.prev = p;
			}

			node.prev = null;
			node.next = head;
			head.prev = node;
			head = node;
		}
	}

	static class LRUNode {
		LRUNode prev, next = null;

		int key = 0, val = 0;

		public LRUNode(int key, int val) {
			this.key = key;
			this.val = val;
		}

	}

	public void printNodes() {
		LRUNode cur = head;
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.next;
		}
	}

	public static void main(String[] args) {
		LRUCache2 cache = new LRUCache2(0);
		cache.set(2, 1);
		cache.set(1, 1);
		System.out.println(cache.get(1));
		cache.set(4, 1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
		System.out.println(cache.get(4));
		//		cache.printNodes();

	}
}
