package bTreeLevelOrderTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//
//For example:
//Given binary tree {3,9,20,#,#,15,7},
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its level order traversal as:
//[
//  [3],
//  [9,20],
//  [15,7]
//]
public class Solution {
	// another way is to use a count to count the remaining node in current level
	//  when we are done with current level, set count to the size of the queue 
	//  because now queue contains all nodes from next level
	public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return ret;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		int count = q.size();
		ArrayList<Integer> curLevel = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			TreeNode next = q.poll();
			curLevel.add(next.val);
			if (next.left != null)
				q.offer(next.left);
			if (next.right != null)
				q.offer(next.right);
			if (--count == 0) {
				ret.add(curLevel);
				curLevel = new ArrayList<Integer>();
				count = q.size();
			}
		}
		return ret;
	}

	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
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
				ret.add(curLvl);
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

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);

		ArrayList<ArrayList<Integer>> ret = new Solution().levelOrder2(root);
		System.out.println(ret);
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
