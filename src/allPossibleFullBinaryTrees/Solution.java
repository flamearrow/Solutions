package allPossibleFullBinaryTrees;
//A full binary tree is a binary tree where each node has exactly 0 or 2 children.
//
//        Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
//
//        Each node of each tree in the answer must have node.val = 0.
//
//        You may return the final list of trees in any order.

import treeUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ret = new ArrayList<>();
        if (N == 1) {
            ret.add(new TreeNode(0));
        } else {
            // use one as root, the leftovers are N-1
            for (int leftNodes = 1; leftNodes <= N - 2; leftNodes++) {
                int rightNodes = N - 1 - leftNodes;
                List<TreeNode> leftTress = allPossibleFBT(leftNodes);
                List<TreeNode> rightTress = allPossibleFBT(rightNodes);
                for (TreeNode leftTree : leftTress) {
                    for (TreeNode rightTree : rightTress) {
                        ret.add(new TreeNode(0, leftTree, rightTree));
                    }
                }
            }
        }
        return ret;
    }
}
