package crackingTheCodingInterview.linkedLists;

// 2.5

public class LinkedListAddition {
	// given two linked list representing two numbers, add them
	// i.e l1: 3->2->1, le: 6->5->4
	// return 123+456=579
	// 9->7->5
	Node add1(Node l1, Node l2) {
		int carry = 0;
		Node cur1 = l1, cur2 = l2;
		Node ret = null, retCur = null;
		while (cur1 != null && cur2 != null) {
			if (ret == null) {
				ret = new Node((cur1.val + cur2.val) % 10);
				carry = (cur1.val + cur2.val) / 10;
				retCur = ret;
			} else {
				retCur.next = new Node((cur1.val + cur2.val + carry) % 10);
				carry = (cur1.val + cur2.val + carry) / 10;
				retCur = retCur.next;
			}
			cur1 = cur1.next;
			cur2 = cur2.next;
		}

		Node leftOverCur = cur1 == null ? cur2 == null ? null : cur2 : cur1;

		while (leftOverCur != null) {
			if (ret == null) {
				ret = new Node(leftOverCur.val);
				retCur = ret;
			} else {
				retCur.next = new Node((leftOverCur.val + carry) % 10);
				carry = (leftOverCur.val + carry) / 10;
				retCur = retCur.next;
			}
			leftOverCur = leftOverCur.next;
		}

		if (carry == 1) {
			retCur.next = new Node(1);
		}
		return ret;
	}

	// followUp what if they are reversely stored?
	// i.e l1: 1->2->3, l2: 4->5->6
	// return 5->7->9
	Node add2(Node l1, Node l2) {
		// trick: pad the shorter one with 0s so that we can recurse
		int len1 = len(l1), len2 = len(l2);
		if (len1 < len2) {
			l1 = pad(l1, len2 - len1);
		} else if (len1 > len2) {
			l2 = pad(l2, len1 - len2);
		}
		// now the length is same, we can recurse
		NodeCarryWrap ncw = doAdd(l1, l2);
		Node ret;
		if (ncw.carry == 1) {
			ret = new Node(1);
			ret.next = ncw.n;
		} else {
			ret = ncw.n;
		}
		return ret;
	}

	NodeCarryWrap doAdd(Node l1, Node l2) {
		// both nodes must reach end at the same time
		if (l1 == null) {
			return new NodeCarryWrap(null, 0);
		} else {
			NodeCarryWrap pre = doAdd(l1.next, l2.next);
			Node retNode = new Node((pre.carry + l1.val + l2.val) % 10);
			retNode.next = pre.n;
			NodeCarryWrap ret = new NodeCarryWrap(retNode,
					(pre.carry + l1.val + l2.val) / 10);
			return ret;
		}
	}

	class NodeCarryWrap {
		Node n = null;
		int carry = 0;

		public NodeCarryWrap(Node n, int carry) {
			this.n = n;
			this.carry = carry;
		}
	}

	// pad count 0s in front of head
	Node pad(Node head, int count) {
		Node newHead = new Node(0);
		Node cur = newHead;
		while (--count > 0) {
			cur.next = new Node(0);
			cur = cur.next;
		}
		cur.next = head;
		return newHead;
	}

	int len(Node head) {
		Node cur = head;
		int ret = 0;
		while (cur != null) {
			cur = cur.next;
			ret++;
		}
		return ret;
	}

	public static void main(String[] args) {
		Node l1 = new Node(3);
		// l1.next = new Node(2);
		// l1.next.next = new Node(1);
		Node l2 = new Node(9);
		l2.next = new Node(9);
		l2.next.next = new Node(7);
		// Node sum = new LinkedListAddition().add1(null, l2);
		Node sum = new LinkedListAddition().add2(l1, l2);
		System.out.println(sum);
	}

}
