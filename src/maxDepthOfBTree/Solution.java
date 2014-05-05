package maxDepthOfBTree;

//Given a binary tree, find its maximum depth.
//
//The maximum depth is the number of nodes along the longest path 
// from the root node down to the farthest leaf node.
public class Solution {
	
	public int maxDepth2(TreeNode root) {
		if (root == null)
			return 0;
		int leftDepth = maxDepth2(root.left);
		int rightDepth = maxDepth2(root.right);
		return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
	}
	
	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		return probeDepth(root, 0);
	}

	private int probeDepth(TreeNode root, int lvl) {
		lvl++;
		if ((root.left == null) && (root.right == null)) {
			return lvl;
		} else if (root.left == null) {
			return probeDepth(root.right, lvl);
		} else if (root.right == null) {
			return probeDepth(root.left, lvl);
		} else {
			int leftDepth = probeDepth(root.left, lvl);
			int rightDepth = probeDepth(root.right, lvl);
			return leftDepth > rightDepth ? leftDepth : rightDepth;
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
