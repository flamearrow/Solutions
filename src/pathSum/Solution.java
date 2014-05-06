package pathSum;

//Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
//
//For example:
//Given the below binary tree and sum = 22,
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \      \
//        7    2      1
//return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
public class Solution {

	public boolean hasPathSum2(TreeNode root, int sum) {
		return root != null && doProbe(root, sum);
	}

	boolean doProbe(TreeNode root, int sum) {
		return (root.left == null && root.right == null && root.val == sum)
				|| (root.left != null) && doProbe(root.left, sum - root.val)
				|| (root.right != null) && doProbe(root.right, sum - root.val);
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		return probe(root, 0, sum);
	}

	boolean probe(TreeNode root, int currentSum, int targetSum) {
		if (root.left == null && root.right == null)
			return (currentSum + root.val) == targetSum;
		else if (root.left == null) {
			return probe(root.right, currentSum + root.val, targetSum);
		} else if (root.right == null) {
			return probe(root.left, currentSum + root.val, targetSum);
		} else {
			return probe(root.right, currentSum + root.val, targetSum)
					|| probe(root.left, currentSum + root.val, targetSum);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		boolean b = new Solution().hasPathSum2(root, 1);
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