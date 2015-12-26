package reverseLinkedList;

//Reverse a singly linked list.
public class Solution {
	public ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		return doReverse(head).head;
	}

	// recursively
	WrapNode doReverse(ListNode head) {
		if (head == null) {
			return null;
		}
		WrapNode rest = doReverse(head.next);
		if (rest == null) {
			return new WrapNode(head, head);
		} else {
			head.next = null;
			rest.tail.next = head;
			rest.tail = head;
			return rest;
		}
	}

	// iteratively
	ListNode reverseItr(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

}

class WrapNode {
	ListNode head, tail;

	public WrapNode(ListNode vHead, ListNode vTail) {
		head = vHead;
		tail = vTail;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}