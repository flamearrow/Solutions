package removeDupFromSortedList2;

//Given a sorted linked list, delete all nodes that
// have duplicate numbers, leaving only distinct numbers from the original list.
//
//For example,
//Given 1->2->3->3->4->4->5, return 1->2->5.
//Given 1->1->1->2->3, return 2->3. 
public class Solution {
	public ListNode deleteDuplicates(ListNode head) {
		// instead of removing dups,
		// find distinct nodes that left and link them together
		ListNode ret = null, retTail = null;
		ListNode pre = null, cur = head;
		boolean isDup = false;
		while (cur != null) {
			if (pre != null) {
				if (pre.val == cur.val) {
					isDup = true;
				} else {
					// end of a dup streak
					// can't add cur because cur.next might be dup
					if (isDup) {
						isDup = false;
					}
					// need to append cur to ret
					else {
						if (ret == null) {
							ret = pre;
							retTail = ret;
						} else {
							retTail.next = pre;
							retTail = pre;
						}
					}
				}
			}
			pre = cur;
			cur = cur.next;
		}
		// last one is unique
		if (!isDup && pre != null) {
			if (ret == null) {
				ret = pre;
				retTail = ret;
			} else {
				retTail.next = pre;
				retTail = pre;
			}
		}
		if (retTail != null)
			retTail.next = null;
		return ret;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(2);
//		head.next.next.next = new ListNode(2);
//		head.next.next.next.next = new ListNode(3);
//		head.next.next.next.next.next = new ListNode(4);
//		head.next.next.next.next.next.next = new ListNode(5);
		ListNode ret = new Solution().deleteDuplicates(head);
		System.out.println(ret);
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	@Override
	public String toString() {
		return "" + val;
	}
}
