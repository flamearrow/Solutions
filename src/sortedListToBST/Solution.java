package sortedListToBST;

//Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
public class Solution {
	// use a bottom up approach, we'll be able to do it in O(n) time, need to pass the length first
	// note we need to keep the head static so that it's advancing during the bottom up probing
	// think about bottom up for a tree traversal problem, keep a universal var to keep track
	static ListNode head = null;

	public TreeNode sortedListToBSTNew(ListNode head) {
		if (head == null)
			return null;
		Solution.head = head;
		int len = getLength(head);
		return doSortNew(0, len - 1);
	}

	public TreeNode doSortNew(int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeNode leftChild = doSortNew(start, mid - 1);
		// head is updated!
		TreeNode parent = new TreeNode(head.val);
		parent.left = leftChild;
		head = head.next;
		parent.right = doSortNew(mid + 1, end);
		return parent;
	}

	int getLength(ListNode head) {
		int ret = 0;
		while (head != null) {
			head = head.next;
			ret++;
		}
		return ret;
	}

	// break the original list by having two pointers with different moving speed to find the mid, then recurse
	// time complexity is n/2 + n/4 *2 + n/8 *4 + ... + n/n * n =  O(nLogn)
	public TreeNode sortedListToBST(ListNode head) {
		return doSort(head);
	}

	TreeNode doSort(ListNode head) {
		if (head == null)
			return null;
		if (head.next == null)
			return new TreeNode(head.val);
		ListNode midPre = getMidPre(head);
		ListNode mid = midPre.next;
		ListNode sendHalfHead = mid.next;
		// break the first half!
		midPre.next = null;
		// break the second half!
		mid.next = null;

		TreeNode ret = new TreeNode(mid.val);

		ret.left = doSort(head);
		ret.right = doSort(sendHalfHead);
		return ret;
	}

	// return the previousNode of mid of the linked list
	ListNode getMidPre(ListNode head) {
		if (head == null)
			return null;
		ListNode one = head, two = head, pre = head;
		while (two.next != null) {
			pre = one;
			one = one.next;
			two = two.next;
			if (two.next == null)
				break;
			two = two.next;
		}
		return pre;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		//		head.next.next.next = new ListNode(4);
		//		head.next.next.next.next = new ListNode(5);

		TreeNode toPrint = new Solution().sortedListToBSTNew(head);
		System.out.println(toPrint);
	}

	// /////////////////////////// copy the list to back array
	public TreeNode sortedListToBSTBackArray(ListNode head) {
		if (head == null)
			return null;
		int len = 0;
		ListNode cur = head;
		while (cur != null) {
			cur = cur.next;
			len++;
		}
		ListNode[] nodes = new ListNode[len];
		cur = head;
		int i = 0;
		while (cur != null) {
			nodes[i++] = cur;
			cur = cur.next;
		}
		return createTree(nodes, 0, len - 1);
	}

	TreeNode createTree(ListNode[] nodes, int start, int end) {
		if (start > end)
			return null;
		int root = (end - start) / 2 + start;
		TreeNode ret = new TreeNode(nodes[root].val);
		ret.left = createTree(nodes, start, root - 1);
		ret.right = createTree(nodes, root + 1, end);
		return ret;
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

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}