package constructBTreeFromInOrderAndPostOrder;

import java.util.HashMap;
import java.util.Map;

//Given inorder and postorder traversal of a tree, construct the binary tree.
//
//Note:
//You may assume that duplicates do not exist in the tree.
public class Solution {
	// the idea is to locate the range of post and in order array for each iteration
	// for post order array, the sequence would be [leftSubTree][rightSubTree][root]
	//  to recurse, we need to know the end of leftSubTree and rightSubTree
	//  end of rightSubTree would be index[root]-1
	//  end of leftSubTree would be index[root]-(sizeof(rightSubTree)-1)
	//    where sizeof(rightSubTree) can be calculated from (inorder start and inorder root)
	// similar idea applies to preorder traversal
	public TreeNode buildTree3(int[] inorder, int[] postorder) {
		if (inorder.length == 0)
			return null;
		Map<Integer, Integer> inIndexMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			inIndexMap.put(inorder[i], i);
		}
		return doBuild3(inIndexMap, postorder, 0, inorder.length - 1,
				inorder.length - 1);
	}

	TreeNode doBuild3(Map<Integer, Integer> inIndexMap, int[] postorder,
			int inStart, int inEnd, int postEnd) {
		if (inStart > inEnd)
			return null;
		TreeNode root = new TreeNode(postorder[postEnd]);
		int inIndex = inIndexMap.get(postorder[postEnd]);
		root.left = doBuild3(inIndexMap, postorder, inStart, inIndex - 1,
				postEnd - inEnd + inIndex - 1);
		root.right = doBuild3(inIndexMap, postorder, inIndex + 1, inEnd,
				postEnd - 1);
		return root;
	}

	public TreeNode buildTreeNew(int[] inorder, int[] postorder) {
		if (inorder.length == 0)
			return null;
		Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
		int len = inorder.length;
		for (int i = 0; i < len; i++) {
			inMap.put(inorder[i], i);
		}
		return doBuild(inorder, 0, len - 1, postorder, len - 1, inMap);
	}

	TreeNode doBuild(int[] inorder, int inStart, int inEnd, int[] postOrder,
			int postIndex, Map<Integer, Integer> inMap) {
		TreeNode root = new TreeNode(postOrder[postIndex]);
		int inIndex = inMap.get(postOrder[postIndex]);
		// there is a right sub tree to build
		if (inIndex < inEnd) {
			root.right = doBuild(inorder, inIndex + 1, inEnd, postOrder,
					postIndex - 1, inMap);
		}
		// there is a left sub tree to build, skip the entire right subtree
		if (inIndex > inStart) {
			root.left = doBuild(inorder, inStart, inIndex - 1, postOrder,
					postIndex - inEnd + inIndex - 1, inMap);
		}
		return root;
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return doBuildTree(inorder, postorder, 0, inorder.length - 1,
				postorder.length - 1);
	}

	TreeNode doBuildTree(int[] inorder, int[] postorder, int inStart,
			int inEnd, int postStartFrom) {
		if (inStart == inEnd)
			return new TreeNode(inorder[inStart]);
		if (inStart > inEnd) {
			return null;
		}
		// find rightmost node in post starting from postStartFrom that also
		// falls in the range of inorder[]
		int postIndex = postStartFrom;
		int inIndex;
		here: while (true) {
			for (inIndex = inStart; inIndex <= inEnd; inIndex++) {
				if (inorder[inIndex] == postorder[postIndex]) {
					break here;
				}
			}
			postIndex--;
		}
		TreeNode ret = new TreeNode(postorder[postIndex]);
		postStartFrom--;
		ret.left = doBuildTree(inorder, postorder, inStart, inIndex - 1,
				postStartFrom);
		ret.right = doBuildTree(inorder, postorder, inIndex + 1, inEnd,
				postStartFrom);
		return ret;
	}

	public static void main(String[] args) {
		int[] inorder = { 2, 4, 1, 3, 6, 5 };
		int[] postorder = { 4, 2, 6, 5, 3, 1 };
		TreeNode root = new Solution().buildTree3(inorder, postorder);
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