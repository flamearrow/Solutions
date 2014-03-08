package bTreeInorderTraversal;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
	// Morris in order traversal, no stack
	//  the idea is to link a node's right pointer to its successor so that [each nodes R's left child's right most child is pointing to R]
	//  we do this link only when cur.right == null
	//  then when we actually visited cur.right, we reset the right pointer back to null
	//  when we actually see this linkage, we know R's entire left tree is visited, we just visit R and break the tmp link
	public ArrayList<Integer> inorderTraversalMorris(TreeNode root) {
		TreeNode cur = root, tmp = null;
		ArrayList<Integer> ret = new ArrayList<Integer>();
		while (cur != null) {
			if (cur.left == null) {
				ret.add(cur.val);
				cur = cur.right;
			} else {
				tmp = cur.left;
				while (tmp.right != null && tmp.right != cur) {
					tmp = tmp.right;
				}
				// now tmp's successor should be cur 
				// because tmp is the right most child of cur's left child
				if (tmp.right == null) {
					tmp.right = cur;
					cur = cur.left;
				}
				// tmp.right == cur, tmp is the right most child of cur's left child
				//  and tmp is already visited, we need visit cur
				else {
					ret.add(cur.val);
					cur = cur.right;
					tmp.right = null;
				}
			}
		}
		return ret;
	}

	// from left to right
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		TreeNode cur = root;
		Stack<TreeNode> s = new Stack<TreeNode>();
		while (cur != null) {
			if (cur.left != null) {
				s.push(cur);
				cur = cur.left;
			} else {
				ret.add(cur.val);
				cur = cur.right;
				// go up to the first non empty right child
				while (cur == null && !s.isEmpty()) {
					cur = s.pop();
					ret.add(cur.val);
					cur = cur.right;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		//		root.setLeft(2).setLeft(3).setLeft(4).setLeft(5);
		root.setRight(2).setRight(3).setRight(4).setRight(5);
		for (int i : new Solution().inorderTraversalMorris(root)) {
			System.out.println(i);
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

	public TreeNode setLeft(TreeNode left) {
		this.left = left;
		return this.left;
	}

	public TreeNode setLeft(int val) {
		this.left = new TreeNode(val);
		return this.left;
	}

	public TreeNode setRight(TreeNode right) {
		this.right = right;
		return this.right;
	}

	public TreeNode setRight(int val) {
		this.right = new TreeNode(val);
		return this.right;
	}
}