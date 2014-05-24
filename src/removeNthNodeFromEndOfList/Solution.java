package removeNthNodeFromEndOfList;

//Given a linked list, remove the nth node from the end of list and return its head.
//
//For example,
//
//   Given linked list: 1->2->3->4->5, and n = 2.
//
//   After removing the second node from the end, the linked list becomes 1->2->3->5.
//
//Note:
//Given n will always be valid.
//Try to do this in one pass. 
public class Solution {
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode prob = head;
		for (int i = 0; i < n; i++) {
			prob = prob.next;
		}
		ListNode ret = head, prev = null;
		while (prob != null) {
			prob = prob.next;
			prev = ret;
			ret = ret.next;
		}
		if (prev == null)
			return ret.next;
		else {
			prev.next = prev.next.next;
		}
		return head;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre = head, cur = head;
		while (n > 0) {
			if (cur == null)
				return head;
			cur = cur.next;
			n--;
		}
		if (cur == null)
			head = head.next;
		else {
			while (cur.next != null) {
				cur = cur.next;
				pre = pre.next;
			}

			pre.next = pre.next.next;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.setNext(2);
		// .setNext(3).setNext(4).setNext(5);
		ListNode ret = new Solution().removeNthFromEnd(head, 0);
		while (ret != null) {
			System.out.print(ret.val + " ");
			ret = ret.next;
		}
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	ListNode setNext(int val) {
		next = new ListNode(val);
		return next;
	}
}