package minDepthOfBTree;

//Given a binary tree, find its minimum depth.
//
//The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
public class Solution {
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