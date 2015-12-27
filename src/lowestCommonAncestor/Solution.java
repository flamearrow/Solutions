package lowestCommonAncestor;

import java.util.HashSet;
import java.util.Set;

// find LCA of two nodes
public class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return doShit(root, p, q);
	}

	// return p or q if this branch only contains p or q
	// return there root if it contains their root
	
	// doesn't work if the tree only conains one node - would return that node
	public TreeNode doShit(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == null || q == null) {
			return null;
		}
		if (root == p || root == q) {
			return root;
		}
		TreeNode left = doShit(root.left, p, q);
		TreeNode right = doShit(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}
		return left == null ? left : right;
	}
}

class TmpNode {
	public TreeNode root;
	Set<TreeNode> hit;

	public TmpNode() {
		hit = new HashSet<TreeNode>();
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