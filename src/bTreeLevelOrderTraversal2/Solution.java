package bTreeLevelOrderTraversal2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;


//Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
//
//For example:
//Given binary tree {3,9,20,#,#,15,7},
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its bottom-up level order traversal as:
//[
//  [15,7]
//  [9,20],
//  [3],
//]
public class Solution {
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		// BFS
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return ret;
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		TreeNode spr = new TreeNode(0);
		queue.offer(root);
		queue.offer(spr);
		boolean newLvl = false;
		ArrayList<Integer> curLvl = new ArrayList<Integer>();
		while (queue.peek() != spr) {
			TreeNode next = queue.poll();
			curLvl.add(next.val);
			// end of current level
			if (queue.peek() == spr) {
				queue.poll();
				ret.add(0, curLvl);
				curLvl = new ArrayList<Integer>();
				newLvl = true;
			}

			if (next.left != null) {
				queue.offer(next.left);
			}

			if (next.right != null) {
				queue.offer(next.right);
			}

			if (newLvl) {
				queue.offer(spr);
				newLvl = false;
			}
		}
		return ret;
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
