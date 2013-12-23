package pathSum2;

import java.util.ArrayList;
import java.util.LinkedList;

//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//
//For example:
//Given the below binary tree and sum = 22,
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
//return
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
public class Solution {
	ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		if (root == null)
			return ret;
		LinkedList<Integer> visitedNodes = new LinkedList<Integer>();
		probe(root, 0, sum, visitedNodes);
		return ret;
	}

	void probe(TreeNode root, int currentSum, int targetSum,
			LinkedList<Integer> visitedNodes) {
		if (root.left == null && root.right == null)
			if ((currentSum + root.val) == targetSum) {
				visitedNodes.add(root.val);
				ArrayList<Integer> newList = new ArrayList<Integer>(
						visitedNodes);
				ret.add(newList);
				visitedNodes.removeLast();
			}
		if (root.left != null) {
			visitedNodes.add(root.val);
			probe(root.left, currentSum + root.val, targetSum, visitedNodes);
			visitedNodes.removeLast();
		}
		if (root.right != null) {
			visitedNodes.add(root.val);
			probe(root.right, currentSum + root.val, targetSum, visitedNodes);
			visitedNodes.removeLast();
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
}