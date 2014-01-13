package partitionList;

//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
//You should preserve the original relative order of the nodes in each of the two partitions.
//
//For example,
//Given 1->4->3->2->5->2 and x = 3,
//return 1->2->2->4->3->5
public class Solution {
	public ListNode partition(ListNode head, int x) {
		ListNode leftHead = null, leftTail = null, rightHead = null, rightTail = null;
		ListNode cur = head;
		while (cur != null) {
			if (cur.val < x) {
				if (leftHead == null) {
					leftHead = cur;
					leftTail = cur;
				} else {
					leftTail.next = cur;
					leftTail = cur;
				}
			} else {
				if (rightHead == null) {
					rightHead = cur;
					rightTail = cur;
				} else {
					rightTail.next = cur;
					rightTail = cur;
				}
			}
			cur = cur.next;
		}
		if (rightTail != null)
			rightTail.next = null;
		if (leftTail != null) {
			leftTail.next = rightHead;
			return leftHead;
		} else {
			return rightHead;
		}
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		ListNode newHead = new Solution().partition(head, 2);
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
