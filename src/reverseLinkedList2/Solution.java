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
//1 ¡Ü m ¡Ü n ¡Ü length of list.
public class Solution {

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
		// head.next.next.next = new ListNode(4);
		// head.next.next.next.next = new ListNode(5);

		ListNode newHead = new Solution().reverseBetween(head, 1, 3);
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