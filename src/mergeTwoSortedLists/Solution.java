package mergeTwoSortedLists;

//Merge two sorted linked lists and return it as a new list. 
//The new list should be made by splicing together the nodes of the first two lists.
public class Solution {
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		ListNode ret = null, pre = null;
		while (l1 != null && l2 != null) {
			ListNode next = l1.val < l2.val ? l1 : l2;

			if (pre == null) {
				ret = next;
			} else {
				pre.next = next;
			}
			pre = next;
			if (next == l1)
				l1 = l1.next;
			if (next == l2)
				l2 = l2.next;
		}
		ListNode left = l1 == null ? l2 : l1;
		while (left != null) {
			if (pre == null) {
				ret = left;
			} else {
				pre.next = left;
			}
			pre = left;
			left = left.next;
		}
		return ret;
	}

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
		left.setNext(5).setNext(7);
		ListNode right = new ListNode(3);
		right.setNext(6).setNext(8);
		ListNode head = new Solution().mergeTwoLists2(left, null);
		System.out.println(head);
		ListNode cur = head;
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.next;
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

	public ListNode setNext(int next) {
		this.next = new ListNode(next);
		return this.next;
	}
}
