package binaryTreeLongestConsecutiveSequence;

//Given a binary tree, find the length of the longest consecutive sequence path.
//
//The path refers to any sequence of nodes from some starting node to any node in the 
// tree along the parent-child connections. The longest consecutive path need to be from 
// parent to child (cannot be the reverse).
//
//For example,
//   1
//    \
//     3
//    / \
//   2   4
//        \
//         5
//Longest consecutive sequence path is 3-4-5, so return 3.
//   2
//    \
//     3
//    / 
//   2    
//  / 
// 1
//Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
public class Solution {
	int maxLen = 0;

	public int longestConsecutive(TreeNode root) {
		probe(root, 0, 0);
		return maxLen;
	}

	public void probe(TreeNode current, int length, int pre) {
		if (current == null) {
			return;
		}
		int len = 0;
		if (length > 0 && current.val == pre + 1) {
			len = length + 1;

		} else {
			len = 1;
		}
		if (len > maxLen) {
			maxLen = len;
		}
		probe(current.left, len, current.val);
		probe(current.right, len, current.val);
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