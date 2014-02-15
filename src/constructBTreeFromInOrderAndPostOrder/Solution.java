package constructBTreeFromInOrderAndPostOrder;

import java.util.HashMap;
import java.util.Map;

//Given inorder and postorder traversal of a tree, construct the binary tree.
//
//Note:
//You may assume that duplicates do not exist in the tree.
public class Solution {

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
		int[] inorder = {};
		int[] postorder = {};
		TreeNode root = new Solution().buildTree(inorder, postorder);
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