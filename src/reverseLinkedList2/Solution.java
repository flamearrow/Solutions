package reverseLinkedList2;

//Reverse a linked list from position m to n. Do it in-place and in one-pass.
//
//For example:
//Given 1->2->3->4->5->NULL, m = 2 and n = 4,
//
//return 1->4->3->2->5->NULL.
//
//Note:
//Given m, n satisfy the following condition:
//1 <= m <= n <= length of list.
public class Solution {
	public ListNode reverseBetween2(ListNode head, int m, int n) {
		int curPtr = 1;
		ListNode preHead = null, pre = null, cur = head;
		while (cur != null) {
			if (curPtr < m - 1) {
				pre = cur;
				cur = cur.next;
				curPtr++;
			} else if (curPtr == m - 1) {
				preHead = cur;
				pre = cur;
				cur = cur.next;
				curPtr++;
			} else if (curPtr == m) {
				// head of reversed sublist
				pre = cur;
				cur = cur.next;
				curPtr++;
			} else if (curPtr <= n) {
				// reverse and relink
				ListNode next = cur.next;
				cur.next = pre;
				pre = cur;
				cur = next;
				// end of list, connect preHead
				if (curPtr == n) {
					ListNode tail = preHead == null ? head : preHead.next;
					if (preHead == null)
						head = pre;
					else
						preHead.next = pre;
					tail.next = cur;
				}
				curPtr++;
			} else
				break;
		}
		return head;
	}

	public ListNode reverseBetweenNew(ListNode head, int m, int n) {
		if (m == n)
			return head;
		ListNode pre = null, cur = head;
		ListNode preTail = null, postHead = null;
		ListNode midTail = null;
		int curPtr = 0;
		while (curPtr <= n) {
			if (curPtr < m - 1) {
				pre = cur;
				cur = cur.next;
			} else if (curPtr == m - 1) {
				preTail = pre;
				midTail = cur;
				pre = cur;
				cur = cur.next;
			} else if (curPtr >= m && curPtr < n) {
				ListNode tmp = cur.next;
				cur.next = pre;
				pre = cur;
				cur = tmp;
			} else if (curPtr == n) {
				postHead = cur;
			}
			curPtr++;
		}
		if (preTail != null) {
			preTail.next = pre;
		}
		midTail.next = postHead;
		if (preTail != null) {
			return head;
		} else {
			return pre;
		}
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode cur = head;
		ListNode pre = null;
		int i = 1;
		while (i < m) {
			pre = cur;
			cur = cur.next;
			i++;
		}

		ListNode leftEnd = pre;
		pre = cur;
		cur = cur.next;
		i = m;
		while (i < n) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
			i++;
		}
		// now cur is rightEnd, pre is end of swapped list
		// we didn't touch original head
		if (leftEnd != null) {
			leftEnd.next.next = cur;
			leftEnd.next = pre;
		}
		// we touched original head
		else {
			head.next = cur;
			head = pre;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);

		ListNode newHead = new Solution().reverseBetween2(head, 1, 1);
		ListNode cur = newHead;
		while (cur != null) {
			System.out.print(cur.val + " ");
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
}