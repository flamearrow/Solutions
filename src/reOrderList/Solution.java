package reOrderList;

//Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
//reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
//
//You must do this in-place without altering the nodes' values.
//
//For example,
//Given {1,2,3,4}, reorder it to {1,4,2,3}.

public class Solution {
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

	private ListNode reverse(ListNode head) {
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