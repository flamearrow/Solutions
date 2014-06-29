package partitionList;

//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
//You should preserve the original relative order of the nodes in each of the two partitions.
//
//For example,
//Given 1->4->3->2->5->2 and x = 3,
//return 1->2->2->4->3->5
public class Solution {
	public ListNode partition2(ListNode head, int x) {
		if (head == null)
			return null;
		ListNode tail = head;
		int size = 0, cnt = 0;
		while (tail.next != null) {
			size++;
			tail = tail.next;
		}
		ListNode prev = null, cur = head, last = tail;
		ListNode ret = null;
		while (cnt <= size) {
			cnt++;
			// move cur to last
			if (cur.val >= x) {
				ListNode next = cur.next;
				last.next = cur;
				last = cur;
				if (prev != null)
					prev.next = next == null ? tail : next;
				cur.next = null;
				cur = next;
			} else {
				if (prev == null)
					ret = cur;
				prev = cur;
				cur = cur.next;
			}
		}
		return ret == null ? head : ret;
	}

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
		ListNode head = new ListNode(1);
		head.setNext(2);
		//		head.setNext(4).setNext(3).setNext(2).setNext(5).setNext(2);

		ListNode newHead = new Solution().partition2(head, 2);
		while (newHead != null) {
			System.out.print(newHead.val + " ");
			newHead = newHead.next;
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

	ListNode setNext(int i) {
		next = new ListNode(i);
		return next;
	}
}
