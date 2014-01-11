package sortedArrayToBST;

//Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
public class Solution {
	public TreeNode sortedArrayToBST(int[] num) {
		return createTree(num, 0, num.length - 1);
	}

	TreeNode createTree(int[] nodes, int start, int end) {
		if (start > end)
			return null;
		int root = (end - start) / 2 + start;
		TreeNode ret = new TreeNode(nodes[root]);
		ret.left = createTree(nodes, start, root - 1);
		ret.right = createTree(nodes, root + 1, end);
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