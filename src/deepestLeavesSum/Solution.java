package deepestLeavesSum;
//Given a binary tree, return the sum of values of its deepest leaves.
//
//
//        Example 1:
//
//
//
//        Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
//        Output: 15
//
//
//        Constraints:
//
//        The number of nodes in the tree is between 1 and 10^4.
//        The value of nodes is between 1 and 100.


import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int deepestLeavesSum(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int nodesLeftThisLvl = 1;
        int lastLvlSum = 0;
        int currentLvlSum = 0;
        while (!queue.isEmpty()) {
            TreeNode next = queue.removeFirst();
            currentLvlSum += next.val;
            if (next.left != null) {
                queue.addLast(next.left);
            }
            if (next.right != null) {
                queue.addLast(next.right);
            }
            if (--nodesLeftThisLvl == 0) {
                lastLvlSum = currentLvlSum;
                currentLvlSum = 0;
                nodesLeftThisLvl = queue.size();
            }
        }

        return lastLvlSum;
    }
}