package rotateList;

//Given a list, rotate the list to the right by k places, where k is non-negative.
//
//For example:
//Given 1->2->3->4->5->NULL and k = 2,
//return 4->5->1->2->3->NULL.
public class Solution {
	public ListNode rotateRight2(ListNode head, int n) {
		if (head == null)
			return null;
		if (n == 0)
			return head;
		int size = 0;
		ListNode prob = head;
		while (prob != null) {
			size++;
			prob = prob.next;
		}
		n = n % size;
		if (n == 0)
			return head;
		prob = head;
		while (n > 0) {
			prob = prob.next;
			n--;
		}

		ListNode tail = head;
		while (prob.next != null) {
			tail = tail.next;
			prob = prob.next;
		}
		ListNode ret = tail.next;
		tail.next = null;
		prob.next = head;
		return ret;
	}

	// note: n might be greater than size! in which case we need to start from
	// head.next
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null)
			return null;
		if (n == 0)
			return head;
		ListNode tail = head;
		ListNode cur = head;
		while (n > 0) {
			if (tail != null)
				tail = tail.next;
			// if we haven't hit tail, then need to move to head.next
			else
				tail = head.next;
			n--;
		}
		// n % size == 0;
		if (tail == null)
			return head;
		while (tail.next != null) {
			tail = tail.next;
			cur = cur.next;
		}
		ListNode newHead = cur.next;
		cur.next = null;
		tail.next = head;
		return newHead;
	}

	public static void main(String... args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		ListNode newHead = new Solution().rotateRight2(head, 3);
		System.out.println(newHead);
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}