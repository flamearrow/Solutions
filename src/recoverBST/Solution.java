package recoverBST;

//Two elements of a binary search tree (BST) are swapped by mistake.
//
//Recover the tree without changing its structure.
//Note:
//A solution using O(n) space is pretty straight forward. Could you devise
// 	a constant space solution? 
public class Solution {
	// we use potential Wrong node for the following tree
	//    2
	//  3   1
	// in which case 2 is potential wrong node: it breaks the BST property, but the real wrong nodes are 1 and 3 
	TreeNode pre = null;
	TreeNode wrongNode1 = null, wrongNode2 = null;
	TreeNode potentialWrongNode = null;

	public void recoverTree(TreeNode root) {
		doDFSProbe(root);
		if (wrongNode2 == null)
			wrongNode2 = potentialWrongNode;
		int tmp = wrongNode1.val;
		wrongNode1.val = wrongNode2.val;
		wrongNode2.val = tmp;
	}

	void doDFSProbe(TreeNode root) {
		if (root.left != null) {
			doDFSProbe(root.left);
		}
		if (pre != null) {
			if (pre.val > root.val) {
				if (wrongNode1 == null) {
					wrongNode1 = pre;
					potentialWrongNode = root;
				} else {
					wrongNode2 = root;
				}
			}
			pre = root;
		} else {
			pre = root;
		}
		if (root.right != null)
			doDFSProbe(root.right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(1);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		new Solution().recoverTree(root);
		System.out.println(root);
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
		return "" + val;
	}
}
