package kSmallestNumberInATree;

import java.util.LinkedList;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.setLeft(3).setLeft(1);
		root.left.setRight(4);
		for (int i : new Solution().smallestKValues(root, 3)) {
			System.out.println(i);
		}
	}

	// this is to find the smallest k numbers
	public List<Integer> smallestKValues(TreeNode root, int k) {
		List<Integer> ret = new LinkedList<Integer>();
		findShit(root, k, ret);
		return ret;
	}

	// find k numbers smaller than target from the subtree rooting at root
	// if the size of subtree is smaller than k return the additional number
	// needed to added to k
	void findShit(TreeNode root, int k, List<Integer> found) {
		if (root == null) {
			return;
		}
		findShit(root.left, k, found);
		int hitIntLeft = found.size();
		if (hitIntLeft < k) {
			found.add(root.val);
			findShit(root.right, k - found.size(), found);
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

	TreeNode setLeft(int aLeft) {
		left = new TreeNode(aLeft);
		return left;
	}

	TreeNode setRight(int aRight) {
		right = new TreeNode(aRight);
		return right;
	}
}