package binaryTreeUpsideDown;

//Given a binary tree where all the right nodes are either leaf nodes with a 
// sibling (a left node that shares the same parent node) or empty, 
// flip it upside down and turn it into a tree where the original right nodes 
// turned into left leaf nodes. Return the new root.
//
//For example:
//Given a binary tree {1,2,3,4,5},
//    1
//   / \
//  2   3
// / \
//4   5
//return the root of the binary tree [4,5,2,#,#,3,1].
//   4
//  / \
// 5   2
//    / \
//   3   1  
public class Solution {
	TreeNode ret = null;
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		reverseTree(root);
		return ret;
	}
	
	TreeNode reverseTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		// so right might be null while left is not null
		if (root.left == null) {
			ret = new TreeNode(root.val);
			return ret;
		}
		// must have left
		TreeNode parent = reverseTree(root.left);
		parent.left = root.right == null ? null : new TreeNode(root.right.val);
		parent.right = new TreeNode(root.val);
		return parent.right;
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