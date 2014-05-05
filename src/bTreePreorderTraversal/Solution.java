package bTreePreorderTraversal;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
	// we only push current to stack when we go left, if we go right don't push
	public ArrayList<Integer> preorderTraversal2(TreeNode root) {
		TreeNode cur = root;
		Stack<TreeNode> s = new Stack<TreeNode>();
		ArrayList<Integer> ret = new ArrayList<Integer>();
		while (cur != null) {
			ret.add(cur.val);
			if (cur.left != null || cur.right != null) {
				if (cur.left == null) {
					cur = cur.right;
				} else {
					s.push(cur);
					cur = cur.left;
				}
			} else {
				while (cur.right == null && !s.isEmpty()) {
					cur = s.pop();
				}
				cur = cur.right;
			}
		}
		return ret;
	}

	// from high to low
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode cur = root;
		while (cur != null) {
			ret.add(cur.val);
			if (cur.left != null) {
				s.push(cur);
				cur = cur.left;
				continue;
			}
			if (cur.right != null) {
				cur = cur.right;
				continue;
			} else {
				if (s.isEmpty())
					cur = null;
				else {
					cur = s.pop().right;
					while (cur == null && !s.isEmpty()) {
						cur = s.pop().right;
					}
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		TreeNode n = new TreeNode(1);
		//		n.setLeft(4).setLeft(2);
		n.setRight(3);
		for (int i : new Solution().preorderTraversal2(n)) {
			System.out.print(i + " ");
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