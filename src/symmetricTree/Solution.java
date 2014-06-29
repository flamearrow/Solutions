package symmetricTree;

import java.util.ArrayList;
import java.util.LinkedList;

//Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
//For example, this binary tree is symmetric:
//
//    1
//   / \
//  2   2
// / \ / \
//3  4 4  3
//
//But the following is not:
//
//    1
//   / \
//  2   2
//   \   \
//   3    3
//
//Note:
//Bonus points if you could solve it both recursively and iteratively. 
public class Solution {
	public boolean isSymmetric2(TreeNode root) {
		return root == null || isReverse(root.left, root.right);
	}

	boolean isReverse(TreeNode left, TreeNode right) {
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		return (left.val == right.val) && isReverse(left.left, right.right)
				&& isReverse(left.right, right.left);
	}

	// non recursive solution, use BFS, check a level forms a palindrome
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode lvlSpr = new TreeNode(0);
		TreeNode nullChildSpr = new TreeNode(0);
		queue.add(root);
		queue.add(lvlSpr);
		ArrayList<TreeNode> currentLvl = new ArrayList<TreeNode>();
		boolean newLvl = false;
		while (queue.peek() != lvlSpr) {
			TreeNode next = queue.remove();
			currentLvl.add(next);

			if (queue.peek() == lvlSpr) {
				newLvl = true;
				queue.poll();

				if ((next != root) && !checkCurrentLvl(currentLvl)) {
					return false;
				}
				currentLvl.clear();
			}
			if (next != nullChildSpr) {
				if (next.left != null)
					queue.add(next.left);
				else
					queue.add(nullChildSpr);

				if (next.right != null)
					queue.add(next.right);
				else
					queue.add(nullChildSpr);
			}

			if (newLvl) {
				queue.add(lvlSpr);
				newLvl = false;
			}
		}

		return true;
	}

	boolean checkCurrentLvl(ArrayList<TreeNode> currentLvl) {
		if (currentLvl.size() % 2 != 0)
			return false;
		int start = 0, end = currentLvl.size() - 1;
		while (start < end) {
			if (currentLvl.get(start) == currentLvl.get(end)) {
				start++;
				end--;
			} else if ((currentLvl.get(start) != currentLvl.get(end))
					&& (currentLvl.get(start).val == currentLvl.get(end).val)) {
				start++;
				end--;
			} else
				return false;
		}
		return true;
	}

	// recursive solution
	public boolean isSymmetricResursion(TreeNode root) {
		if (root == null)
			return true;
		return checkIsSyemmetric(root.left, root.right);
	}

	boolean checkIsSyemmetric(TreeNode left, TreeNode right) {
		if ((left == null) && (right == null)) {
			return true;
		} else if ((left == null) || (right == null)) {
			return false;
		} else if (left.val == right.val) {
			return checkIsSyemmetric(left.left, right.right)
					&& checkIsSyemmetric(left.right, right.left);
		}
		// left and right have differernt value
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(44);
		root.right.right = new TreeNode(3);
		System.out.println(new Solution().isSymmetric(root));
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
