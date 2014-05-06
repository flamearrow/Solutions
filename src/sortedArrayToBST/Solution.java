package sortedArrayToBST;

//Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
public class Solution {
	public TreeNode sortedArrayToBST2(int[] num) {
		return doConvert(num, 0, num.length - 1);
	}

	TreeNode doConvert(int[] num, int start, int end) {
		if (start == end)
			return new TreeNode(num[start]);
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = doConvert(num, start, mid - 1);
		root.right = doConvert(num, mid + 1, end);
		return root;
	}

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