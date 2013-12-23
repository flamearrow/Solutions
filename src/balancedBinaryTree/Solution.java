package balancedBinaryTree;

//Given a binary tree, determine if it is height-balanced.
//
//For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
public class Solution {
	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;

		if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1)
			return false;
		else
			return isBalanced(root.left) && isBalanced(root.right);
	}

	int maxDepth(TreeNode root) {
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
			return leftDpth > rightDpth ? leftDpth : rightDpth;
		} else if (root.left != null) {
			return probe(root.left, lvl + 1);
		} else {
			return probe(root.right, lvl + 1);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(3);
		root.left.left.left = new TreeNode(4);
		root.left.left.right = new TreeNode(4);

		System.out.println(new Solution().isBalanced(root));

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