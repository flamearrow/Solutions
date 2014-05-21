package reOrderList;

//Given a singly linked list L: L0��L1������Ln-1��Ln,
//reorder it to: L0��Ln��L1��Ln-1��L2��Ln-2����
//
//You must do this in-place without altering the nodes' values.
//
//For example,
//Given {1,2,3,4}, reorder it to {1,4,2,3}.

public class Solution {
	// brute force: get second half, reverse it, link with first half
	public void reorderList2(ListNode head) {
		if (head == null)
			return;
		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// now slow.next is the head of second half
		ListNode secHalf = slow.next;
		slow.next = null;
		secHalf = reverse2(secHalf);
		// now merge head and secHalf
		ListNode prev = head;
		while (secHalf != null) {
			ListNode next = prev.next;
			prev.next = secHalf;
			secHalf = secHalf.next;
			prev.next.next = next;
			prev = next;
		}
	}

	ListNode reverse2(ListNode secHalf) {
		ListNode cur = secHalf, prev = null;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
	public void reorderList(ListNode head) {
		if (head == null || head.next == null)
			return;
		ListNode firstHalfHead = head, secondHalfHead = head;
		while (secondHalfHead != null && secondHalfHead.next != null) {
			firstHalfHead = firstHalfHead.next;
			secondHalfHead = secondHalfHead.next.next;
		}
		secondHalfHead = firstHalfHead.next;
		firstHalfHead.next = null;
		secondHalfHead = reverseNonRecur(secondHalfHead);
		ListNode cur = head;
		while (secondHalfHead != null) {
			ListNode tmp = cur.next;
			cur.next = secondHalfHead;
			secondHalfHead = secondHalfHead.next;
			cur.next.next = tmp;
			cur = tmp;
		}

	}

	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		} else {
			ListNode newTail = head.next;
			ListNode newHead = reverse(head.next);
			newTail.next = head;
			head.next = null;
			return newHead;
		}
	}

	private ListNode reverseNonRecur(ListNode head) {
		if (head == null)
			return head;
		ListNode pre = null;
		ListNode cur = head;
		while (cur.next != null) {
			ListNode tmp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = tmp;
		}
		cur.next = pre;
		return cur;
	}

	public static void main(String[] args) {
		ListNode head = createList(2);
		printList(head);
		Solution s = new Solution();
		s.reorderList(head);
		printList(head);
	}

	static ListNode createList(int[] arr) {
		ListNode head = new ListNode(arr[0]);
		ListNode cur = head;
		for (int i = 1; i < arr.length; i++) {
			cur.next = new ListNode(arr[i]);
			cur = cur.next;
		}
		return head;
	}

	static ListNode createList(int len) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		for (int i = 1; i < len; i++) {
			cur.next = new ListNode(i);
			cur = cur.next;
		}
		return head;
	}

	static void printList(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}
		System.out.println();
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
