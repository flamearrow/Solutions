package closestBSTValue;

//Given a non-empty binary search tree and a target value, 
// find the value in the BST that is closest to the target.
//
//Note:
//Given target value is a floating point.
//You are guaranteed to have only one unique value in the BST that is closest to the target.
public class Solution {
	// the trap is you need to go through the end of tree to determin a min
	// 10
	// 5
	// 9
	// target is 9.1
	// the time you find diff is decreasing(from |10-9.1| to |5-9.1| doesn't
	// mean 10 should be the
	// termination point, need to keep that value 0.9 and keep searching
	public int closestValue(TreeNode root, double target) {
		return doFind(root, target, root.val);
	}
	
	// preValue is the minimal target so far
	int doFind(TreeNode cur, double target, int preValue) {
		if(cur == null) {
			return preValue;
		}
		if(Math.abs(cur.val - target) < Math.abs(preValue - target)) {
			preValue = cur.val;
		}
		// keep track and keep searching, only return if we ever hit end
		if(cur.val < target) {
			return doFind(cur.right, target, preValue);
		} else {
			return doFind(cur.left, target, preValue);
		}
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
