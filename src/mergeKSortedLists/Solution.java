package mergeKSortedLists;

import java.util.ArrayList;

//Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
public class Solution {
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		ListNode ret = null;
		ListNode cur = null;
		ListNode next = getMin(lists);
		while (next != null) {
			if (ret == null) {
				ret = next;
				cur = ret;
			} else {
				cur.next = next;
				cur = cur.next;
			}
			next = getMin(lists);
		}
		return ret;
	}

	ListNode getMin(ArrayList<ListNode> lists) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < lists.size(); i++) {
			ListNode node = lists.get(i);
			if (node == null)
				continue;
			if (node.val < min) {
				min = node.val;
				minIndex = i;
			}
		}
		if (minIndex == -1)
			return null;
		ListNode ret = lists.get(minIndex);
		lists.remove(minIndex);
		if (ret.next != null)
			lists.add(ret.next);
		ret.next = null;
		return ret;
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		n1.setNext(4).setNext(7);

		ListNode n2 = new ListNode(2);
		n2.setNext(5).setNext(8);

		ListNode n3 = new ListNode(3);
		n3.setNext(6).setNext(9);
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		lists.add(n1);
		lists.add(n2);
		lists.add(n3);
		lists.add(null);
		ListNode ret = new Solution().mergeKLists(lists);
		while (ret != null) {
			System.out.print(ret.val + " ");
			ret = ret.next;
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