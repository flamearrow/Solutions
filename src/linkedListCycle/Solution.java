package linkedListCycle;

//Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
//
//Follow up:
//Can you solve it without using extra space?
public class Solution {
	// head to start of circle is a
	// circle size is c
	// then slow and fast will meet at c-a
	// because a+2x=x+c=>x=c-a

	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null)
			return null;
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast && fast != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast == null)
				break;
			fast = fast.next;
		}

		if (fast == null)
			return null;
		else {
			slow = head;
			while (slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
			return slow;
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
}