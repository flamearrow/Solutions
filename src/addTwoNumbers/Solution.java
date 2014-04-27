package addTwoNumbers;

//You are given two linked lists representing two non-negative numbers. 
// The digits are stored in reverse order and each of their nodes 
// contain a single digit. Add the two numbers and return it as a linked list.
//
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
public class Solution {
	// what if two linked list are stored in normal order?
	//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	//Output: 8 -> 0 -> 7
	public ListNode addTwoNumbersNormal(ListNode l1, ListNode l2) {
		int len1 = calLen(l1);
		int len2 = calLen(l2);
		if (len1 < len2)
			l1 = pad(l1, len2 - len1);
		else if (len1 > len2)
			l2 = pad(l2, len1 - len2);
		// now l1 and l2 are the same length
		TmpNode t = add(l1, l2);
		ListNode ret = null;
		if (t.carry > 0) {
			ret = new ListNode(1);
			ret.next = t.node;
		} else {
			ret = t.node;
		}
		return ret;
	}

	class TmpNode {
		ListNode node;
		int carry;
	}

	TmpNode add(ListNode l1, ListNode l2) {
		TmpNode ret = new TmpNode();
		if (l1.next != null) {
			TmpNode t = add(l1.next, l2.next);
			ret.carry = (t.carry + l1.val + l2.val) / 10;
			ret.node = new ListNode((t.carry + l1.val + l2.val) % 10);
			ret.node.next = t.node;
		} else {
			ret.carry = (l1.val + l2.val) / 10;
			ret.node = new ListNode((l1.val + l2.val) % 10);
		}
		return ret;
	}

	int calLen(ListNode l1) {
		int ret = 0;
		while (l1 != null) {
			ret++;
			l1 = l1.next;
		}
		return ret;
	}

	ListNode pad(ListNode l1, int toPad) {
		ListNode ret = new ListNode(0);
		ListNode cur = ret;
		while (toPad > 1) {
			toPad--;
			cur.next = new ListNode(0);
			cur = cur.next;
		}
		cur.next = l1;
		return ret;
	}

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode ret = null, cur = null, prev = null;
		while (l1 != null || l2 != null) {
			int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val)
					+ carry;
			cur = new ListNode(sum % 10);
			carry = sum / 10;
			if (ret == null) {
				ret = cur;
			} else {
				prev.next = cur;
			}
			prev = cur;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
		if (carry > 0)
			prev.next = new ListNode(1);
		return ret;
	}

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
		ListNode l1 = new ListNode(1);
		l1.setNext(2).setNext(4).setNext(3);
		ListNode l2 = new ListNode(5);
		l2.setNext(6).setNext(4);

		ListNode rst = new Solution().addTwoNumbersNormal(l1, l2);
		while (rst != null) {
			System.out.print(rst.val + " ");
			rst = rst.next;
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

	ListNode setNext(ListNode node) {
		next = node;
		return node;
	}

	ListNode setNext(int nextVal) {
		next = new ListNode(nextVal);
		return next;
	}
}
