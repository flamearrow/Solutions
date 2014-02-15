package constructBTreeFromPreOrderAndInOrder;

import java.util.HashMap;
import java.util.Map;

//Given preorder and inorder traversal of a tree, construct the binary tree.
//
//Note:
//You may assume that duplicates do not exist in the tree. 
// Note: using preorder and postorder traversal can't recreate a tree!
//  1              1
// 2      and       2
// would have same pre and post traversal result

public class Solution {
	public TreeNode buildTreeNew(int[] preorder, int[] inorder) {
		if (preorder.length == 0)
			return null;
		Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
		int len = preorder.length;
		for (int i = 0; i < len; i++) {
			inMap.put(inorder[i], i);
		}

		return doBuild(inorder, 0, len - 1, preorder, 0, inMap);
	}

	TreeNode doBuild(int[] inorder, int inStart, int inEnd, int[] preorder,
			int preIndex, Map<Integer, Integer> inMap) {
		TreeNode root = new TreeNode(preorder[preIndex]);
		int inIndex = inMap.get(preorder[preIndex]);
		// there is a left subtree to build
		if (inIndex > inStart) {
			root.left = doBuild(inorder, inStart, inIndex - 1, preorder,
					preIndex + 1, inMap);
		}
		//there is a right subtree to build, skip the entire left subtree
		if (inIndex < inEnd) {
			root.right = doBuild(inorder, inIndex + 1, inEnd, preorder,
					preIndex + inIndex - inStart + 1, inMap);
		}
		return root;
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return doBuildTree(preorder, inorder, 0, inorder.length - 1, 0);
	}

	TreeNode doBuildTree(int[] preorder, int[] inorder, int inStart, int inEnd,
			int preStartFrom) {
		if (inStart == inEnd)
			return new TreeNode(inorder[inStart]);
		if (inStart > inEnd) {
			return null;
		}
		// find leftmost node in pre starting from preStartFrom that also
		// falls in the range of inorder[]
		int preIndex = preStartFrom;
		int inIndex;
		here: while (true) {
			for (inIndex = inStart; inIndex <= inEnd; inIndex++) {
				if (inorder[inIndex] == preorder[preIndex]) {
					break here;
				}
			}
			preIndex++;
		}
		TreeNode ret = new TreeNode(preorder[preIndex]);
		preStartFrom++;
		ret.left = doBuildTree(preorder, inorder, inStart, inIndex - 1,
				preStartFrom);
		ret.right = doBuildTree(preorder, inorder, inIndex + 1, inEnd,
				preStartFrom);
		return ret;
	}

	public static void main(String[] args) {
		int[] inorder = { 4, 2, 5, 1, 3 };
		int[] preorder = { 1, 2, 4, 5, 3 };
		TreeNode root = new Solution().buildTreeNew(preorder, inorder);
		System.out.println(root);
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