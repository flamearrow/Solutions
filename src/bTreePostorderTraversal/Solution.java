package bTreePostorderTraversal;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
	// when doing post traversal, the idea is to judge if we are going from up or from bottom
	// we do this by compare if current node's left or right sub child is just visited,
	// if it is visited, then we're going from bottom, otherwise we're going from up
	public ArrayList<Integer> postorderTraversal2(TreeNode root) {
		TreeNode cur = root, pre = new TreeNode(-1);
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		while (cur != null) {
			if ((cur.left != null || cur.right != null) && pre != cur.left
					&& pre != cur.right) {
				s.push(cur);
				cur = cur.left != null ? cur.left : cur.right;
			} else {
				ret.add(cur.val);
				pre = cur;
				if (s.isEmpty())
					cur = null;
				else if (cur == s.peek().left) {
					if (s.peek().right == null) {
						cur = s.pop();
					} else {
						cur = s.peek().right;
					}
				} else {
					cur = s.isEmpty() ? null : s.pop();
				}
			}
		}
		return ret;
	}

	// from low to high
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode cur = root;
		TreeNode justAdd = null;
		while (cur != null) {
			// probing left
			// we probe left when
			// 1) right is null && (left is not null && left is not just added)
			// or
			// 2) right is not null && right is not just added && (left is not
			// null && left is not just added)
			if (cur.left != null
					&& justAdd != cur.left
					&& ((cur.right != null && justAdd != cur.right) || cur.right == null)) {
				s.push(cur);
				cur = cur.left;
				continue;
			}
			// probing right
			if (cur.right != null && justAdd != cur.right) {
				s.push(cur);
				cur = cur.right;
				continue;
			}
			// need to add
			else {
				justAdd = cur;
				ret.add(cur.val);
				cur = s.isEmpty() ? null : s.pop();
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		TreeNode n = new TreeNode(1);
		n.setLeft(2).setLeft(4).setRight(6);
		n.setRight(3).setRight(5);
		n.right.right.setLeft(9);
		n.left.left.setLeft(7);
		n.left.setRight(8);
		for (int i : new Solution().postorderTraversal2(n)) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : new Solution().postorderTraversal(n)) {
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