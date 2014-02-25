package shiftLinkedList;

// given a linked list, left shift it for n nodes
// input: 1->2->3->4->5, 3
// output: 4->5->1->2->3
public class Solution {
	Node shift(Node head, int pos) {
		if (pos <= 0)
			return head;
		int len = getLen(head);
		pos = pos % len;
		if (pos == 0)
			return head;
		Node pre = null, cur = head;
		Node ret = head;
		while (pos > 0) {
			pre = cur;
			cur = cur.next;
			pos--;
		}
		ret = cur;
		pre.next = null;
		while (cur.next != null) {
			cur = cur.next;
		}
		cur.next = head;
		return ret;
	}

	int getLen(Node head) {
		int ret = 0;
		while (head != null) {
			ret++;
			head = head.next;
		}
		return ret;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.setNext(2).setNext(3).setNext(4).setNext(5);
		Node newHead = new Solution().shift(head, 1231);
		while (newHead != null) {
			System.out.println(newHead.val + " ");
			newHead = newHead.next;
		}
	}
}

class Node {
	int val;
	Node next;

	public Node(int val) {
		this.val = val;
	}

	Node setNext(int val) {
		next = new Node(val);
		return next;
	}
}
