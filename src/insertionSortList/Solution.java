package insertionSortList;

//Sort a linked list using insertion sort.
public class Solution {
	public ListNode insertionSortList(ListNode head) {
		if (head == null)
			return null;
		return doSort(head, head.next);
	}

	ListNode doSort(ListNode head, ListNode nodeToInsert) {
		if (nodeToInsert == null) {
			return head;
		} else {
			ListNode nextNodeToInsert = nodeToInsert.next;
			ListNode cur = head;
			// new head
			if (nodeToInsert.val < head.val) {
				nodeToInsert.next = head;
				head = nodeToInsert;

			}
			// insert after head
			else {
				while (cur.next.val < nodeToInsert.val) {
					cur = cur.next;
				}
				// insert between cur and cur.next
				nodeToInsert.next = cur.next;
				cur.next = nodeToInsert;
				cur = nodeToInsert.next;
			}

			while (cur.next != nodeToInsert) {
				cur = cur.next;
			}
			cur.next = nextNodeToInsert;
			return doSort(head, nextNodeToInsert);
		}
	}

	public static void main(String[] args) {
		int[] input = { 1, 2, 4, 5, 3, 1, 2, 4, 5, 7, 8, 5, 7, 9, 3, 5, 8, 3,
				5, -123 };
		ListNode l1 = buildList(input);
		Solution s = new Solution();
		printList(s.insertionSortList(l1));
	}

	static ListNode buildList(int[] input) {
		if (input.length == 0)
			return null;
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