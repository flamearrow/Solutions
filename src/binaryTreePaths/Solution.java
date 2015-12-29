package binaryTreePaths;

import java.util.LinkedList;
import java.util.List;
//Given a binary tree, return all root-to-leaf paths.
//
//For example, given the following binary tree:
//
//   1
// /   \
//2     3
// \
//  5
//All root-to-leaf paths are:
//
//["1->2->5", "1->3"]

public class Solution {

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> ret = new LinkedList<>();
		LinkedList<Integer> list = new LinkedList<>();
		doShit(root, ret, list);
		return ret;
	}

	void doShit(TreeNode cur, List<String> ret, LinkedList<Integer> list) {
		if (cur == null) {
			return;
		}
		list.add(cur.val);
		if (cur.left == null && cur.right == null) {
			ret.add(toListString(list));
		}
		if (cur.left != null) {
			doShit(cur.left, ret, list);
		}
		if (cur.right != null) {
			doShit(cur.right, ret, list);
		}
		list.removeLast();
	}

	String toListString(LinkedList<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int i : list) {
			sb.append(i);
			sb.append("->");
		}
		return sb.toString().substring(0, sb.length() - 2);
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