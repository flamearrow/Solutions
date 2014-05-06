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
	public ArrayList<ArrayList<Integer>> pathSum2(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return ret;
		LinkedList<Integer> curPath = new LinkedList<Integer>();
		doProbe(ret, curPath, root, sum);
		return ret;
	}

	void doProbe(ArrayList<ArrayList<Integer>> ret,
			LinkedList<Integer> curPath, TreeNode root, int sum) {
		if (root.left == null && root.right == null) {
			if (root.val == sum) {
				curPath.add(root.val);
				ret.add(new ArrayList<Integer>(curPath));
				curPath.removeLast();
			}
			return;
		}

		if (root.left != null) {
			curPath.add(root.val);
			doProbe(ret, curPath, root.left, sum - root.val);
			curPath.removeLast();
		}

		if (root.right != null) {
			curPath.add(root.val);
			doProbe(ret, curPath, root.right, sum - root.val);
			curPath.removeLast();
		}
	}

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