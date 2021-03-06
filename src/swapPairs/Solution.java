package swapPairs;

//Given a linked list, swap every two adjacent nodes and return its head.
//
//For example,
//Given 1->2->3->4, you should return the list as 2->1->4->3.
//
//Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
public class Solution {
	// consider normal case first
	public ListNode swapPairsNew(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode n1 = head, n2 = head.next;
		ListNode retHead = n2;
		while (true) {
			ListNode tmp = n2.next;
			n2.next = n1;
			n1.next = tmp;
			tmp = n1;
			n1 = n1.next;
			if (n1 == null)
				break;
			n2 = n1.next;
			if (n2 == null)
				break;
			tmp.next = n2;
		}
		return retHead;
	}

	public ListNode swapPairs(ListNode head) {
		if (head == null)
			return head;
		ListNode pre = null, first = head, second = head.next, retHead = head;
		while (second != null) {
			first.next = second.next;
			second.next = first;
			if (pre != null) {
				pre.next = second;
			} else {
				retHead = second;
			}
			pre = first;
			first = pre.next;
			if (first == null)
				second = null;
			else
				second = first.next;
		}
		return retHead;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.setNext(2);
		// head.setNext(2).setNext(3).setNext(4).setNext(5).setNext(6);
		ListNode newHead = new Solution().swapPairs(null);
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