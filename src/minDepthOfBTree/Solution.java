package minDepthOfBTree;

import java.util.LinkedList;
import java.util.Queue;

//Given a binary tree, find its minimum depth.
//
//The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
public class Solution {
	// this is faster
	public int minDepthBFS(TreeNode root) {
		if (root == null)
			return 0;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		TreeNode separator = new TreeNode(0);
		q.offer(root);
		q.offer(separator);
		int lvl = 1;
		boolean lvlEnd = false;
		while (!q.isEmpty()) {
			TreeNode next = q.poll();
			if (next == separator) {
				break;
			}
			if (q.peek() == separator) {
				lvlEnd = true;
				q.poll();
			}
			if (next.left == null && next.right == null) {
				return lvl;
			}
			if (next.left != null) {
				q.offer(next.left);
			}
			if (next.right != null) {
				q.offer(next.right);
			}
			if (lvlEnd) {
				q.offer(separator);
				lvlEnd = false;
				lvl++;
			}
		}
		return lvl;
	}

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		return probe(root, 0);
	}

	int probe(TreeNode root, int lvl) {
		if (root.left == null && root.right == null)
			return lvl + 1;
		if (root.left != null && root.right != null) {
			int leftDpth = probe(root.left, lvl + 1);
			int rightDpth = probe(root.right, lvl + 1);
			return leftDpth < rightDpth ? leftDpth : rightDpth;
		} else if (root.left != null) {
			return probe(root.left, lvl + 1);
		} else {
			return probe(root.right, lvl + 1);
		}
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