package detectLinkedListCycle;

public class Solution {
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
