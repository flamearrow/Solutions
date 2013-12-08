package bTreeInorderTraversal;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
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