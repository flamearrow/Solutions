package mergeTwoSortedLists;

//Merge two sorted linked lists and return it as a new list. 
//The new list should be made by splicing together the nodes of the first two lists.
public class Solution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = null, pre = null;
		if (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				head = l1;
				pre = head;
				l1 = l1.next;
			} else {
				head = l2;
				pre = head;
				l2 = l2.next;
			}
		} else if (l1 != null) {
			head = l1;
			pre = head;
			l1 = l1.next;
		} else if (l2 != null) {
			head = l2;
			pre = head;
			l2 = l2.next;
		} else {
			return null;
		}

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				pre.next = l1;
				l1 = l1.next;
			} else {
				pre.next = l2;
				l2 = l2.next;
			}
			pre = pre.next;
		}

		while (l1 != null) {
			pre.next = l1;
			l1 = l1.next;
			pre = pre.next;
		}

		while (l2 != null) {
			pre.next = l2;
			pre = pre.next;
			l2 = l2.next;
		}

		return head;
	}

	public static void main(String[] args) {
		ListNode left = new ListNode(2);
		ListNode right = new ListNode(3);
		ListNode head = new Solution().mergeTwoLists(left, right);
		System.out.println(head);
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
