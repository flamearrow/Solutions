package binaryTreeVerticalOrderTraversal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

//Given a binary tree, return the vertical order traversal of its nodes' values. 
//(ie, from top to bottom, column by column).
//
//If two nodes are in the same row and column, the order should be from left to right.
//
//Examples:
//Given binary tree [3,9,20,null,null,15,7],
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its vertical order traversal as:
//[
//  [9],
//  [3,15],
//  [20],
//  [7]
//]
//Given binary tree [3,9,20,4,5,2,7],
//    _3_
//   /   \
//  9    20
// / \   / \
//4   5 2   7
//return its vertical order traversal as:
//[
//  [4],
//  [9],
//  [3,5,2],
//  [20],
//  [7]
//]
public class Solution {

	// do BFS
	public List<List<Integer>> verticalOrder(TreeNode root) {
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		if (root == null) {
			return new LinkedList<List<Integer>>();
		}
		Map<TreeNode, Integer> column = new HashMap<>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		column.put(root, 0);
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			int index = column.get(cur);
			if (!map.containsKey(index)) {
				map.put(index, new LinkedList<Integer>());
			}
			map.get(index).add(cur.val);
			if (cur.left != null) {
				q.add(cur.left);
				column.put(cur.left, index - 1);
			}

			if (cur.right != null) {
				q.add(cur.right);
				column.put(cur.right, index + 1);
			}
		}
		return convert(map);
	}

	public List<List<Integer>> verticalOrderDFS(TreeNode root) {
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		doShit(root, 0, map);
		return convert(map);
	}

	void doShit(TreeNode cur, int index, Map<Integer, List<Integer>> map) {
		if (cur == null) {
			return;
		}
		doShit(cur.left, index - 1, map);
		if (!map.containsKey(index)) {
			map.put(index, new LinkedList<Integer>());
		}
		map.get(index).add(cur.val);
		doShit(cur.right, index + 1, map);
	}

	List<List<Integer>> convert(TreeMap<Integer, List<Integer>> map) {
		List<List<Integer>> list = new LinkedList<>();
		for (int key : map.navigableKeySet()) {
			list.add(map.get(key));
		}
		return list;
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
