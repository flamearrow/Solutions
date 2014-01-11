package uniqueBinarySearchTrees2;

import java.util.ArrayList;
//Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
//
//For example,
//Given n = 3, your program should return all 5 unique BST's shown below.
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3

public class Solution {
	public ArrayList<TreeNode> generateTrees(int n) {
		ArrayList<TreeNode> ret = new ArrayList<TreeNode>();
		if (n == 0) {
			ret.add(null);
		} else if (n == 1) {
			ret.add(new TreeNode(1));
		} else {
			ArrayList<TreeNode> preRst = generateTrees(n - 1);
			for (TreeNode preTree : preRst) {
				// 1: new node is new root, pre root is left child
				TreeNode newRoot1 = new TreeNode(n);
				TreeNode newTree = copyTree(preTree);
				newRoot1.left = newTree;
				ret.add(newRoot1);

				// 2: new node is the right most child of pre tree
				TreeNode newRoot2 = copyTree(preTree);
				TreeNode rightMost = newRoot2;
				while (rightMost.right != null)
					rightMost = rightMost.right;
				rightMost.right = new TreeNode(n);
				ret.add(newRoot2);

				// 3: if pre root has right child, the new node would be the
				// right child, pre right child is now the left child of new
				// node
				// then we continue probing, new node can replace each node of
				// preTree's right branch
				TreeNode rightProbe = preTree.right;
				while (rightProbe != null) {
					TreeNode newRight = new TreeNode(n);
					TreeNode newRoot3n = copyTree(preTree);
					TreeNode newRootProb = newRoot3n.right;
					TreeNode newRootProbPre = newRoot3n;
					while (newRootProb.val != rightProbe.val) {
						newRootProbPre = newRootProbPre.right;
						newRootProb = newRootProb.right;
					}
					newRootProbPre.right = newRight;
					newRight.left = newRootProb;
					ret.add(newRoot3n);
					rightProbe = rightProbe.right;
				}
			}
		}
		return ret;
	}

	public TreeNode copyTree(TreeNode from) {
		TreeNode ret = new TreeNode(from.val);
		if (from.left != null) {
			ret.left = copyTree(from.left);
		}
		if (from.right != null) {
			ret.right = copyTree(from.right);
		}
		return ret;
	}

	public static void main(String[] args) {
		ArrayList<TreeNode> trees = new Solution().generateTrees(4);
		System.out.println(trees);
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
}