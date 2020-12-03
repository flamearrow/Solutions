package largestBSTSubtree;

import treeUtils.TreeNode;

import java.util.HashMap;
import java.util.Map;

//Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
//
//        A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
//
//        The left subtree values are less than the value of their parent (root) node's value.
//        The right subtree values are greater than the value of their parent (root) node's value.
//        Note: A subtree must include all of its descendants.
//
//        Follow up: Can you figure out ways to solve it with O(n) time complexity?
public class Solution {
    public int largestBSTSubtree(TreeNode root) {
        Result result = searchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (result.isBST) {
            return result.size;
        } else {
            int leftResult = largestBSTSubtree(root.left);
            int rightResult = largestBSTSubtree(root.right);
            return Math.max(leftResult, rightResult);
        }
    }

    static class Result {
        int size;
        boolean isBST;

        public Result(int size, boolean isBST) {
            this.size = size;
            this.isBST = isBST;
        }
    }

    Result searchTree(TreeNode root, int min, int max) {
        if (root == null) {
            return new Result(0, true);
        } else {
            Result leftResult = searchTree(root.left, min, root.val);
            Result rightResult = searchTree(root.right, root.val, max);
            int size = leftResult.size + rightResult.size + 1;
            boolean isBST;
            if (root.val <= min || root.val >= max) {
                isBST = false;
            } else {
                isBST = leftResult.isBST && rightResult.isBST;
            }
            return new Result(size, isBST);

        }
    }
}
