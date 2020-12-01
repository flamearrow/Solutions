package countCompleteTreeNodes;
//Given a complete binary tree, count the number of nodes.
//
//        Note:
//
//        Definition of a complete binary tree from Wikipedia:
//        In a complete binary tree every level, except possibly the last, is completely filled,
//        and all nodes in the last level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.
//

import treeUtils.TreeNode;

public class Solution {
    // binary search on width
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftTreeHeight = height(root.left);
        int rightTreeHeight = height(root.right);

        // left Tree full
        if (leftTreeHeight == rightTreeHeight) {
            if (leftTreeHeight == 0) {
                return 1;
            } else {
                return (1 << leftTreeHeight) + countNodes(root.right);
            }
        }
        // left tree not full, right tree full, but one level shorter than left tree
        else {
            if (rightTreeHeight == 0) {
                return 2;
            } else {
                return (1 << rightTreeHeight) + countNodes(root.left);
            }
        }
    }

    int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + height(root.left);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, 2, 3);
        root.left.setLeft(4);
        root.left.setRight(5);
        root.right.setLeft(6);


        System.out.println(new Solution().countNodes(root));
    }
}
