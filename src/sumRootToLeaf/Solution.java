package sumRootToLeaf;


//Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
//
//An example is the root-to-leaf path 1->2->3 which represents the number 123.
//
//Find the total sum of all root-to-leaf numbers.
//
//For example,
//
//    1
//   / \
//  2   3
//The root-to-leaf path 1->2 represents the number 12.
//The root-to-leaf path 1->3 represents the number 13.
//
//Return the sum = 12 + 13 = 25.
public class Solution {
	int sum = 0;

	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		countSum(root, 0);
		return sum;
	}

	void countSum(TreeNode root, int cur) {
		cur = cur * 10 + root.val;
		if (root.left != null) {
			countSum(root.left, cur);
		}
		if (root.right != null) {
			countSum(root.right, cur);
		}
		if (root.left == null && root.right == null) {
			sum += cur;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		// root.left = new TreeNode(2);
		// root.right = new TreeNode(3);
		System.out.println(new Solution().sumNumbers(root));
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