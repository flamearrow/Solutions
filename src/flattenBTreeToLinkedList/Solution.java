package flattenBTreeToLinkedList;

//Given a binary tree, flatten it to a linked list in-place.
//For example,
//Given
//
//         1
//        / \
//       2   5
//      / \   \
//     3   4   6
//The flattened tree should look like:
//   1
//    \
//     2
//      \
//       3
//        \
//         4
//          \
//           5
//            \
//             6
public class Solution {
	// in-place version: note that if we do a deep first traversal we will find the flattened tree
	//  dft is actually taking the root of left sub tree in each round and attach right tree to the right most child of left sub tree
	// example tree transformation:
	//     1
	//    / \
	//   2   5
	//  / \   \
	// 3   4   6
		
	//      1
	//       \
	//        2
	//       / \   
	//      3   4
	//		     \
	//		      5
	//             \
	//              6          
	
	//  1
	//   \
	//    2
	//     \
	//      3
	//       \
	//        4
	//         \
	//          5
	//           \
	//            6
	public void flatten(TreeNode root) {
		if(root == null) return;
		TreeNode left = root.left;
		if (left != null) {
			TreeNode right = root.right;
			TreeNode rMostOfLeftSubTree = left;
			while (rMostOfLeftSubTree.right != null)
				rMostOfLeftSubTree = rMostOfLeftSubTree.right;
			root.right = root.left;
			root.left = null;
			rMostOfLeftSubTree.right = right;
		}
		flatten(root.right);
	}

	// This is actually using a stack to store internal values and not in-place
	public void flattenRecNonInPlace(TreeNode root) {
		doFlattern(root);
	}

	TreeNode doFlattern(TreeNode root) {
		if (root == null)
			return null;
		if (root.left == null) {
			if (root.right != null)
				return doFlattern(root.right);
			else
				return root;
		} else {
			TreeNode tmp = root.right;
			TreeNode tmpRightMost = doFlattern(root.left);
			tmpRightMost.right = tmp;
			root.right = root.left;
			root.left = null;
			if (tmp == null)
				return tmpRightMost;
			else
				return doFlattern(tmp);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
//		root.left = new TreeNode(2);
//		root.left.right = new TreeNode(3);
//		root.left.right = new TreeNode(4);
//		root.right = new TreeNode(5);
//		root.right.right = new TreeNode(6);
		new Solution().flatten(root);
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