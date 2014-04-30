package reverseNodesInKGroup;

//Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//
//If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
//
//You may not alter the values in the nodes, only nodes itself may be changed.
//
//Only constant memory is allowed.
//
//For example,
//Given this linked list: 1->2->3->4->5
//
//For k = 2, you should return: 2->1->4->3->5
//
//For k = 3, you should return: 3->2->1->4->5
public class Solution {
	public ListNode reverseKGroup2(ListNode head, int k) {
		if (head == null)
			return null;
		if (head.next == null)
			return head;
		ListNode start = head, end = head, pre = null;
		ListNode ret = head;
		int left = k - 1;
		while (end != null) {
			// found a group
			if (left == 0) {
				left = k - 1;
				if (pre == null) {
					ret = end;
				} else {
					pre.next = end;
				}
				pre = start;
				ListNode c = start.next;
				while (c != end) {
					ListNode next = c.next;
					c.next = start;
					start = c;
					c = next;
				}
				// now c == end
				ListNode next = c.next;
				c.next = start;
				start = next;
				pre.next = start;
				end = start;
			} else {
				left--;
				end = end.next;
			}
		}
		return ret;
	}

	// don't use recursion to reverse a list
	// iterative reverse requires three pointers, pre cur and next
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode start = head, end = head;
		ListNode pre = null;
		ListNode retHead = null;
		int len = k - 1;
		while (end != null) {
			if (len > 0) {
				end = end.next;
				len--;
			}
			// start and end covers k nodes
			// we need to reverse start to end
			else {
				if (pre != null) {
					pre.next = end;
				} else {
					retHead = end;
				}
				pre = start;
				ListNode c = start.next;
				while (start != end) {
					ListNode next = c.next;
					c.next = start;
					start = c;
					c = next;
				}
				start = c;
				end = c;
				pre.next = c;
				len = k - 1;
			}
		}
		return retHead == null ? head : retHead;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		//		head.setNext(2).setNext(3).setNext(4).setNext(5).setNext(6).setNext(7)
		//				.setNext(8);
		ListNode ret = new Solution().reverseKGroup2(head, 1);
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