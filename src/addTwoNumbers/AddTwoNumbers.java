package addTwoNumbers;

//You are given two linked lists representing two non-negative numbers. 
//The digits are stored in reverse order and each of their nodes 
//contain a single digit. Add the two numbers and return it as a linked list.
//
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
public class AddTwoNumbers {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(9);
		l1.setNext(9).setNext(9);
		ListNode l2 = new ListNode(1);
		ListNode rst = new AddTwoNumbers().addTwoNumbersForward(l1, l2);
		while (rst != null) {
			System.out.print(rst.val + " ");
			rst = rst.next;
		}
	}

	// 999
	// 1
	// 1000
	// do recurse
	public ListNode addTwoNumbersForward(ListNode l1, ListNode l2) {
		ListNode p1 = l1, p2 = l2;
		while (p1 != null && p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		ListNode left = p1 == null ? p2 == null ? null : p2 : p1;
		ListNode toExtend = left == p1 ? l2 : left == p2 ? l1 : null;
		ListNode noChanged = left == null ? null : toExtend == l1 ? l2 : l1;
		while (left != null) {
			left = left.next;
			ListNode node = new ListNode(0);
			node.next = toExtend;
			toExtend = node;
		}
		Tmp t = null;
		if (toExtend == null) {
			t = addRec(l1, l2);
		} else {
			t = addRec(toExtend, noChanged);
		}
		if (t.mCarry == 1) {
			ListNode ret = new ListNode(1);
			ret.next = t.mNode;
			return ret;
		} else {
			return t.mNode;
		}
	}

	class Tmp {
		int mCarry;
		ListNode mNode;

		Tmp(int carry, ListNode node) {
			mCarry = carry;
			mNode = node;
		}
	}

	Tmp addRec(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return new Tmp(0, null);
		}
		Tmp rst = addRec(l1.next, l2.next);
		int carry = (rst.mCarry + l1.val + l2.val) / 10;
		ListNode newNode = new ListNode((rst.mCarry + l1.val + l2.val) % 10);
		newNode.next = rst.mNode;
		return new Tmp(carry, newNode);
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode ret = null, cur = null;
		int carry = 0;
		while (l1 != null && l2 != null) {
			ListNode newNode = new ListNode((l1.val + l2.val + carry) % 10);
			if (cur == null) {
				ret = newNode;
				cur = newNode;
			} else {
				cur.next = newNode;
				cur = newNode;
			}
			carry = (l1.val + l2.val + carry) / 10;
			l1 = l1.next;
			l2 = l2.next;
		}
		ListNode left = l1 == null ? (l2 == null ? null : l2) : l1;
		while (left != null) {
			ListNode node = new ListNode((left.val + carry) % 10);
			carry = (left.val + carry) / 10;
			if (cur == null) {
				ret = node;
				cur = node;
			} else {
				cur.next = node;
				cur = node;
			}
			left = left.next;
		}
		if (carry == 1) {
			cur.next = new ListNode(1);
		}
		return ret;
	}
}
