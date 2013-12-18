package binaryTreeMaximumPathSum;

import java.util.HashMap;
import java.util.Map;

//Given a binary tree, find the maximum path sum.
//
//The path may start and end at any node in the tree.
//
//For example:
//Given the below binary tree,
//
//      1
//     / \
//    2   3
//
//Return 6. 
public class Solution {
	int max = Integer.MIN_VALUE;
	Map<TreeNode, Integer> pathLens = new HashMap<TreeNode, Integer>();

	public int maxPathSum(TreeNode root) {
		probe(root);
		return max;
	}

	int probe(TreeNode root) {
		if (root == null)
			return 0;
		else if (pathLens.containsKey(root))
			return pathLens.get(root);
		else {
			int leftMax = probe(root.left);
			int rightMax = probe(root.right);
			int newSinglePathMax = 0;
			int newPathMax = root.val;
			if (leftMax < 0 && rightMax < 0) {
				newSinglePathMax = root.val;
			} else {
				newSinglePathMax = (leftMax > rightMax ? leftMax : rightMax)
						+ root.val;
				newPathMax += (leftMax > 0 ? leftMax : 0)
						+ (rightMax > 0 ? rightMax : 0);
			}
			pathLens.put(root, newSinglePathMax);
			if (newPathMax > max)
				max = newPathMax;
			return newSinglePathMax;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		System.out.println(new Solution().maxPathSum(root));
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "[" + val + "]";
	}
}
