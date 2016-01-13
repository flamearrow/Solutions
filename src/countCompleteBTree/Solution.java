package countCompleteBTree;

/**
 * Created by flamearrow on 1/12/16.
 */
public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int hL = height(root.left);
        int hR = height(root.right);
        if (hL == hR) {// left subtree is full
            return 1 << hL + countNodes(root.right);
        } else {// right subtree is full, but one level shorter than left subtree
            return 1 << hR + countNodes(root.left);
        }
    }

    int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + height(root.left);
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