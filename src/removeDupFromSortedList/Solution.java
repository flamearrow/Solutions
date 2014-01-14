package removeDupFromSortedList;

//Given a sorted linked list, delete all duplicates such that each 
// element appear only once.
//
//For example,
//Given 1->1->2, return 1->2.
//Given 1->1->2->3->3, return 1->2->3. 
public class Solution {
	public ListNode deleteDuplicates(ListNode head) {
		ListNode pre = null, cur = head;
		while (cur != null) {
			if (pre != null) {
				if (pre.val == cur.val) {
					cur = cur.next;
					pre.next = cur;
				} else {
					pre = cur;
					cur = cur.next;
				}
			} else {
				pre = cur;
				cur = cur.next;
			}
		}
		return head;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(3);
		new Solution().deleteDuplicates(head);
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
