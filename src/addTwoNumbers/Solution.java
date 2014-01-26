package addTwoNumbers;

//You are given two linked lists representing two non-negative numbers. 
// The digits are stored in reverse order and each of their nodes 
// contain a single digit. Add the two numbers and return it as a linked list.
//
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
public class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode retHead = null;
		ListNode retPtr = null;
		int carry = 0;
		while (l1 != null || l2 != null) {
			int sum = 0;
			if (l1 != null)
				sum += l1.val;
			if (l2 != null)
				sum += l2.val;
			sum += carry;
			int newRst = sum % 10;
			carry = sum / 10;
			if (retHead == null) {
				retHead = new ListNode(newRst);
				retPtr = retHead;
			} else {
				retPtr.next = new ListNode(newRst);
				retPtr = retPtr.next;
			}
			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}

		if (carry > 0) {
			retPtr.next = new ListNode(1);
		}
		return retHead;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(3);
		l1.setNext(4).setNext(5);
		ListNode l2 = new ListNode(7);
		l2.setNext(6).setNext(5);

		ListNode rst = new Solution().addTwoNumbers(l1, null);
		System.out.println(rst);
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	ListNode setNext(ListNode node) {
		next = node;
		return node;
	}

	ListNode setNext(int nextVal) {
		next = new ListNode(nextVal);
		return next;
	}
}
