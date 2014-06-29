package bTreeZigZagLvlTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//Given a binary tree, return the zigzag level order traversal of its nodes' values. 
// (ie, from left to right, then right to left for the next level and alternate between).
//
//For example:
//Given binary tree {3,9,20,#,#,15,7},
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//return its zigzag level order traversal as:
//
//[
//  [3],
//  [20,9],
//  [15,7]
//]

public class Solution {
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder2(TreeNode root) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return ret;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		LinkedList<Integer> cur = new LinkedList<Integer>();
		int left = 1;
		boolean reverse = false;
		while (!q.isEmpty()) {
			TreeNode next = q.poll();
			if (next.left != null)
				q.offer(next.left);
			if (next.right != null)
				q.offer(next.right);
			if (reverse)
				cur.addFirst(next.val);
			else
				cur.addLast(next.val);
			if (--left == 0) {
				reverse = !reverse;
				left = q.size();
				ret.add(new ArrayList<Integer>(cur));
				cur.clear();
			}
		}
		return ret;
	}

	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return ret;
		// BFS
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode spr = new TreeNode(0);
		queue.add(root);
		queue.add(spr);
		boolean newLvl = false;
		boolean leftToRight = true;
		ArrayList<Integer> current = new ArrayList<Integer>();

		while (queue.peek() != spr) {
			TreeNode next = queue.remove();
			if (leftToRight)
				current.add(next.val);
			else
				current.add(0, next.val);

			if (queue.peek() == spr) {
				queue.poll();
				newLvl = true;
				ret.add(current);
				current = new ArrayList<Integer>();
				leftToRight = !leftToRight;
			}

			if (next.left != null)
				queue.add(next.left);
			if (next.right != null)
				queue.add(next.right);

			if (newLvl) {
				queue.add(spr);
				newLvl = false;
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		// root.left = new TreeNode(9);
		// root.right = new TreeNode(20);
		// root.right.left = new TreeNode(15);
		// root.right.right = new TreeNode(7);
		ArrayList<ArrayList<Integer>> ret = new Solution()
				.zigzagLevelOrder(root);
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
