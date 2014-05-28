package detectLinkedListCycle;

//Given a linked list, return the if the linked list has a cycle
public class Solution {
	
	public boolean hasCycle2(ListNode head) {
		if (head == null)
			return false;
		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				return true;
		}
		return false;
	}

	public ListNode detectCycle2(ListNode head) {
		if (head == null)
			return null;
		ListNode slow = head, fast = head;
		boolean hasCycle = false;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				hasCycle = true;
				break;
			}
		}
		if (hasCycle) {
			slow = head;
			while (slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
			return slow;
		} else {
			return null;
		}
	}
	
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null)
			return false;
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast && fast != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast == null)
				return false;
			fast = fast.next;
		}

		if (fast == null)
			return false;
		else {
			return true;
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
