package pathSum3;

import treeUtils.TreeNode;

//You are given a binary tree in which each node contains an integer value.
//
//        Find the number of paths that sum to a given value.
//
//        The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
//
//        The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
//
//        Example:
//
//        root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//           10
//          /  \
//         5   -3
//        / \    \
//        3   2   11
//       / \   \
//      3  -2   1
//
//        Return 3. The paths that sum to 8 are:
//
//        1.  5 -> 3
//        2.  5 -> 2 -> 1
//        3. -3 -> 11


public class Solution {
    public int pathSum(TreeNode root, int sum) {
        return pathSumWithRoot(root, sum) + pathSumWithoutRoot(root, sum);
    }

    int pathSumWithRoot(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        } else {
            int childSum = sum - root.val;
            int ret = 0;
            // one path found
            if (childSum == 0) {
                ret = 1;
            }
            ret += pathSumWithRoot(root.left, childSum);
            ret += pathSumWithRoot(root.right, childSum);
            return ret;
        }
    }


    int pathSumWithoutRoot(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(5, null, null), new TreeNode(-3, null, null));
        root.left.setLeft(3).setLeft(3);
        root.left.left.setRight(-2);
        root.left.setRight(2).setRight(1);
        root.right.setRight(11);
        System.out.println(new Solution().pathSum(root, 8));
    }
}
