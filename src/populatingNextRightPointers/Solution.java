package populatingNextRightPointers;

import java.util.ArrayDeque;
import java.util.Queue;

//Given a binary tree
//
//struct TreeLinkNode {
//  TreeLinkNode *left;
//  TreeLinkNode *right;
//  TreeLinkNode *next;
//}
//Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
//Initially, all next pointers are set to NULL.
//
//Note:
//
//You may only use constant extra space.
//You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
//For example,
//Given the following perfect binary tree,
//     1
//   /  \
//  2    3
// / \  / \
//4  5  6  7
//After calling your function, the tree should look like:
//     1 -> NULL
//   /  \
//  2 -> 3 -> NULL
// / \  / \
//4->5->6->7 -> NULL
public class Solution {

	// this is a FULL btree, if cur.next.left == null, 
	//  then cur.next.right should also be null
	public void connect2(TreeLinkNode root) {
		if (root == null)
			return;
		if (root.left != null) {
			root.left.next = root.right;
		}
		if (root.right != null) {
			root.right.next = root.next == null ? null : root.next.left;
		}
		connect(root.left);
		connect(root.right);
	}

	// a handy way to bfs a tree with the awareness of level is to add a separator at the end of each level,
	// can just new a dummy separator node and use == to judge level...
	//  there's a handier way: just keep a count for number of nodes in level starting with 1(root), 
	//  decrement it when polling a node, when it drops to 0 reset to q.size()
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		// BFS with a node to represent level
		Queue<TreeLinkNode> q = new ArrayDeque<TreeLinkNode>();
		TreeLinkNode separator = new TreeLinkNode(0);
		q.offer(root);
		q.offer(separator);
		while (!q.isEmpty()) {
			TreeLinkNode nextNode = q.poll();
			// done
			if (nextNode == separator)
				break;
			boolean isEndOfCurrentLvl = (q.peek() == separator);
			if (nextNode.left != null)
				q.offer(nextNode.left);
			if (nextNode.right != null)
				q.offer(nextNode.right);
			if (!isEndOfCurrentLvl) {
				nextNode.next = q.peek();
			} else {
				q.poll();
				q.offer(separator);
			}
		}
	}

	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.right = new TreeLinkNode(3);
		root.left.left = new TreeLinkNode(4);
		root.left.right = new TreeLinkNode(5);
		root.right.left = new TreeLinkNode(6);
		root.right.right = new TreeLinkNode(7);
		new Solution().connect2(root);
		short t = '\n';
		System.out.println(t);
	}
}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}
