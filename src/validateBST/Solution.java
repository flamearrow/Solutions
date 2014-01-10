package validateBST;

//Given a binary tree, determine if it is a valid binary search tree (BST).
//
//Assume a BST is defined as follows:
//
//   The left subtree of a node contains only nodes with keys less than the node's key.
//   The right subtree of a node contains only nodes with keys greater than the node's key.
//   Both the left and right subtrees must also be binary search trees.

public class Solution {
	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
		return doValidateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean doValidateBST(TreeNode root, int lowerBound, int upperBound) {
		boolean leftValid, rightValid;
		if (root.left == null) {
			leftValid = true;
		} else if (root.left != null && root.val > root.left.val
				&& root.left.val > lowerBound) {
			leftValid = doValidateBST(root.left, lowerBound, root.val);
		} else {
			leftValid = false;
		}
		if (root.right == null) {
			rightValid = true;
		} else if (root.right != null && root.val < root.right.val
				&& root.right.val < upperBound) {
			rightValid = doValidateBST(root.right, root.val, upperBound);
		} else {
			rightValid = false;
		}
		return leftValid && rightValid;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		// root.left = new TreeNode(2);
		// root.right = new TreeNode(6);
		// root.left.left = new TreeNode(1);
		// root.left.right = new TreeNode(3);
		// root.right.left = new TreeNode(5);
		// root.right.right = new TreeNode(7);
		boolean s = new Solution().isValidBST(root);
		System.out.println(s);
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "" + val;
	}
}
