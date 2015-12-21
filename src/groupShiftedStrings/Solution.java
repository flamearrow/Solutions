package groupShiftedStrings;

//Given a binary tree, count the number of uni-value subtrees.
//
//A Uni-value subtree means all nodes of the subtree have the same value.
//
//For example:
//Given binary tree,
//              5
//             / \
//            1   5
//           / \   \
//          5   5   5
//return 4.
public class Solution {
	public int countUnivalSubtrees(TreeNode root) {
		if (root == null)
			return 0;
		int ret = uni(root) ? 1 : 0;
		return ret + countUnivalSubtrees(root.left)
				+ countUnivalSubtrees(root.right);
	}

	boolean uni(TreeNode root) {
		int val = root.val;
		if (root.left != null) {
			if (root.left.val != val || !uni(root.left)) {
				return false;
			}
		}
		if (root.right != null) {
			if (root.right.val != val || !uni(root.right)) {
				return false;
			}
		}
		return true;
	}

	public int countUnivalSubtrees2(TreeNode root) {
		Tmp t = doRecurse(root);
		return t.uniCount;
	}

	Tmp doRecurse(TreeNode root) {
		if (root == null) {
			return new Tmp(true, 0);
		} else {
			Tmp l = doRecurse(root.left);
			Tmp r = doRecurse(root.right);
			boolean isUni = l.isUni && r.isUni
					&& (root.left == null ? true : root.left.val == root.val)
					&& (root.right == null ? true : root.right.val == root.val);
			int count = l.uniCount + r.uniCount;
			if (isUni) {
				count++;
			}
			return new Tmp(isUni, count);
		}
	}
}

class Tmp {
	boolean isUni;
	int uniCount;

	public Tmp(boolean u, int c) {
		isUni = u;
		uniCount = c;
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
