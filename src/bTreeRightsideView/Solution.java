package bTreeRightsideView;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//
//        For example:
//        Given the following binary tree,
//        1            <---
//        /   \
//        2     3         <---
//        \     \
//        5     4       <---
//        You should return [1, 3, 4].
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        int levelResidue = 1;
        List<Integer> ret = new LinkedList<>();
        if(root == null) {
            return ret;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode next = q.poll();
            if (next.left != null) {
                q.add(next.left);
            }
            if (next.right != null) {
                q.add(next.right);
            }
            levelResidue--;
            if (levelResidue == 0) {
                ret.add(next.val);
                levelResidue = q.size();
            }
        }
        return ret;
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