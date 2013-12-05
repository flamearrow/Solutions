package sortList;

//Sort a linked list in O(n log n) time using constant space complexity.
public class Solution {
	// merge two sub list per run, sub list size ranges from 1->2->4->...n
	public ListNode sortList(ListNode head) {
		int size = 0;
		ListNode next = head;
		while (next != null) {
			next = next.next;
			size++;
		}
		return doMergeInPlace(head, 1, size);
	}

	ListNode doMergeInPlace(ListNode head, int subListSize, int size) {

		// need to merge every two sub list of subListSize
		if (subListSize < size) {
			ListNode newHead = head;
			ListNode cur = head;
			ListNode prevTail = null;
			while (cur != null) {
				ListNode list1 = cur;
				ListNode nextStart = null;
				// check i < size in case we run over
				for (int i = 0; i < subListSize && cur != null; i++) {
					cur = cur.next;
				}
				// now cur is at start of list2
				ListNode list2 = cur;

				int list1Pass = 0;
				int list2Pass = 0;
				// merge list1 and list2, note there're up to subListSize nodes
				// in each list

				// have head node in place
				if (list2 == null || list1.val < list2.val) {
					newHead = list1;
					list1 = list1.next;
					list1Pass++;
				} else {
					newHead = list2;
					list2 = list2.next;
					list2Pass++;
				}
				cur = newHead;
				while (list1Pass < subListSize && list2Pass < subListSize
						&& list2 != null) {
					if (list1.val < list2.val) {
						cur.next = list1;
						list1 = list1.next;
						list1Pass++;
					} else {
						cur.next = list2;
						list2 = list2.next;
						list2Pass++;
					}
					cur = cur.next;
				}
				// copy tail
				while (list1Pass < subListSize) {

					cur.next = list1;
					cur = cur.next;
					if (list1 == null)
						break;
					list1 = list1.next;
					list1Pass++;
				}
				while (list2Pass < subListSize && list2 != null) {

					cur.next = list2;
					cur = cur.next;
					list2 = list2.next;
					list2Pass++;
				}
				// if part of list1 left, now list2 would point to null or start
				// of next nodes
				// if part of list2 left, need to copy the tail of list2, then
				// get the next as nextStart
				nextStart = list2;
				// now cur should point to tail of the big list combined by two
				// sublists
				if (prevTail != null) {
					prevTail.next = newHead;
				}
				// if prefTail is null, then this is the first list, we need to
				// record head
				else {
					head = newHead;
				}
				if (cur != null)
					cur.next = null;
				prevTail = cur;
				cur = nextStart;
			}
			return doMergeInPlace(head, subListSize * 2, size);
		}
		// already in place!
		else {
			return head;
		}
	}

	public static void main(String[] args) {
		// int[] input = { 4, 19, 14, 5, -3, 1, 8, 5, 11, 15 };
		int[] input = { 1 };
		ListNode l1 = buildList(input);
		Solution s = new Solution();
		printList(s.sortList(l1));
	}

	static ListNode buildList(int[] input) {
		ListNode head = new ListNode(input[0]);
		ListNode cur = head;
		for (int i = 1; i < input.length; i++) {
			ListNode newNode = new ListNode(input[i]);
			cur.next = newNode;
			cur = cur.next;
		}
		return head;
	}

	static void printList(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.err.println(cur.val);
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