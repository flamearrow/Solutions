package inorderSuccessorBST;

//Given a binary search tree and a node in it, 
// find the in-order successor of that node in the BST.
//
//Note: If the given node has no in-order successor in the tree, return null.
public class Solution {
	public static void main(String[] args) {

	}

	TreeNode buf = null;

	public TreeNode inorderSuccessor(TreeNode cur, TreeNode hit) {
		if (cur == null) {
			return null;
		}
		TreeNode proLeft = inorderSuccessor(cur.left, hit);
		if (proLeft != null) {
			return proLeft;
		}
		if (buf == hit) {
			return cur;
		}
		buf = cur;
		return inorderSuccessor(cur.right, hit);
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