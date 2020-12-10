package binaryTreeLongestConsecutiveSequenceII;

import treeUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
//
//        Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
//
//        Example 1:
//
//        Input:
//        1
//        / \
//        2   3
//        Output: 2
//        Explanation: The longest consecutive path is [1, 2] or [2, 1].
//
//
//        Example 2:
//
//        Input:
//        2
//        / \
//        1   3
//        Output: 3
//        Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
//
//
//        Note: All the values of tree nodes are in the range of [-1e7, 1e7].
public class Solution {
    public int longestConsecutive(TreeNode root) {
        Result result = findMaxLen(root);
        List<String> list = new ArrayList<>();

//        list.stream().flatMap()

        return Math.max(Math.max(result.withHeadDecreasingLen, result.withHeadIncreasingLen),
                result.nonHeadLen);
    }

    Result findMaxLen(TreeNode root) {
        if (root == null) {
            return new Result(0, 0, 0);
        }

        Result leftResult = null, rightResult = null;
        int withHeadIncreaseLen = 1;
        int withHeadDecreasingLen = 1;
        int nonHeadLen = 1;

        if (root.left != null) {
            leftResult = findMaxLen(root.left);
            if (root.val == root.left.val + 1) {
                withHeadIncreaseLen = Math.max(leftResult.withHeadIncreasingLen + 1,
                        withHeadIncreaseLen);
            }
            if (root.val == root.left.val - 1) {
                withHeadDecreasingLen = Math.max(leftResult.withHeadDecreasingLen + 1,
                        withHeadDecreasingLen);
            }
            nonHeadLen = Math.max(nonHeadLen, leftResult.nonHeadLen);
        }

        if (root.right != null) {
            rightResult = findMaxLen(root.right);
            if (root.val == root.right.val + 1) {
                withHeadIncreaseLen = Math.max(rightResult.withHeadIncreasingLen + 1,
                        withHeadIncreaseLen);
            } else if (root.val == root.right.val - 1) {
                withHeadDecreasingLen = Math.max(rightResult.withHeadDecreasingLen + 1,
                        withHeadDecreasingLen);
            }
            nonHeadLen = Math.max(nonHeadLen, rightResult.nonHeadLen);
        }
        nonHeadLen = Math.max(nonHeadLen, withHeadDecreasingLen + withHeadIncreaseLen - 1);
        return new Result(withHeadIncreaseLen, withHeadDecreasingLen, nonHeadLen);

    }

    class Result {
        // length of path increasing to the current root
        int withHeadIncreasingLen;
        // length of path decreasing to the current root
        int withHeadDecreasingLen;
        // length of path increasing from one branch to current root then decreasing to the other
        // branch
        int nonHeadLen;

        public Result(int withHeadIncreasingLen, int withHeadDecreasingLen, int nonHeadLen) {
            this.withHeadIncreasingLen = withHeadIncreasingLen;
            this.withHeadDecreasingLen = withHeadDecreasingLen;
            this.nonHeadLen = nonHeadLen;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, 4, 3);
        root.right.setRight(2);
        System.out.println(new Solution().longestConsecutive(root));
    }

}
