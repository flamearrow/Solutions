package constructBSTFromPreOrder;

import java.util.Stack;

// construct a BST from preOrder traversal
public class Solution {
	// use a stack to keep track of last parent
	static TreeNode buildTree(int[] preOrder) {
		if (preOrder.length == 0)
			return null;
		int cur = 0;
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode ret = null;
		while (cur < preOrder.length) {
			TreeNode curNode = new TreeNode(preOrder[cur]);
			if (s.isEmpty()) {
				ret = curNode;
				s.push(curNode);
			} else {
				// curNode is left child
				if (s.peek().val > curNode.val) {
					s.peek().left = curNode;
					s.push(curNode);
				}
				// curNode is somewhere in the right sub tree
				else {
					TreeNode pre = null;
					// we find the first node N whose value is bigger then current
					// then we know current is the rightmost child of N's left sub tree
					while (!s.isEmpty() && s.peek().val < curNode.val) {
						pre = s.pop();
					}
					pre.right = curNode;
					s.push(curNode);
				}
			}
			cur++;
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] preOrder = { 1, 4, 3, 2 };
		TreeNode root = buildTree(preOrder);
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
}