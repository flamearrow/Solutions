package closestBSTValueII;

import java.util.LinkedList;
import java.util.List;

//Given a non-empty binary search tree and a target value, 
// find k values in the BST that are closest to the target.
//
//Note:
//Given target value is a floating point.
//You may assume k is always valid, that is: k ≤ total nodes.
//You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
//Follow up:
//Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
public class Solution {
	// if the list is sorted, the result must be a consecutive sub array within
	// the sorted list
	// we just need to move the head and tail of the list back and forth to find
	// the correct numbers
	// first just find the smallest k numbers from the list, then check
	// |target-head| and |target-tail|
	// if |target-head| > |target-tail| then pop out head and add a new tail,
	// keep doing the shit
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		LinkedList<Integer> ret = new LinkedList<>();
		doShit(root, target, k, ret);
		return ret;
	}

	// find k smallest numbers under root, return true if list contains correct
	// numbers
	boolean doShit(TreeNode root, double target, int k, LinkedList<Integer> list) {
		if (root == null) {
			return false;
		}

		// the k numbers can be found right left subtree
		// we have already moved back and forth on left sub and find the
		// termination condition
		if (doShit(root.left, target, k, list)) {
			return true;
		}

		// otherwise we need to add tail and remove head if needed
		// if we already find k numbers, changes are they are already valid, might return true here
		if (list.size() == k) {
			// no conditions met, move tail and add head, continue on right
			if (Math.abs(list.getFirst() - target) > Math
					.abs(root.val - target)) {
				list.removeFirst();
				list.addLast(root.val);
			} 
			// hit and return true
			else {
				return true;
			}
		} else {
			// otherwise we haven't got k numbers yet, keep searching
			list.addLast(root.val);
		}
		
		return doShit(root.right, target, k, list);
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