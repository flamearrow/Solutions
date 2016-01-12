package kthLargsetInABTree;

import java.util.Iterator;
import java.util.Stack;

//Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
//        Note:
//        You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.setLeft(1).setRight(2).setRight(3).setRight(4);
        root.setLeft(4).setLeft(1).setRight(2).setRight(3);
        root.setRight(9).setLeft(8).setLeft(7).setLeft(6);
        for (int i = 1; i <= 9; i++) {
            System.out.println(new Solution().kthSmallest2(root, i));
        }
    }

    // use a recursive call
    public int kthSmallest2(TreeNode root, int k) {
        return doFindShit(root, k).node.val;
    }

    TmpNode doFindShit(TreeNode root, int k) {
        if (root == null) {
            return new TmpNode(null, 0);
        }
        TmpNode leftVal = doFindShit(root.left, k);
        if (leftVal.node != null) {
            return new TmpNode(leftVal.node, 0);
        } else {
            if (leftVal.count == k - 1) {
                return new TmpNode(root, 0);
            } else {
                TmpNode rightVal = doFindShit(root.right, k - leftVal.count - 1);
                if (rightVal.node != null) {
                    return new TmpNode(rightVal.node, 0);
                } else {
                    return new TmpNode(null, leftVal.count + rightVal.count + 1);
                }
            }
        }
    }

    class TmpNode {
        TreeNode node;
        int count;

        public TmpNode(TreeNode vNode, int vCount) {
            node = vNode;
            count = vCount;
        }
    }


    public int kthSmallest(TreeNode root, int k) {
        Iterator<Integer> itr = new BTreeIterator(root);
        int ret = 0;
        while (k > 0) {
            ret = itr.next();
            k--;
        }
        return ret;
    }

    class BTreeIterator implements Iterator<Integer> {
        Stack<TreeNode> stack;

        public BTreeIterator(TreeNode root) {
            stack = new Stack<>();
            TreeNode cur = root;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }

        @Override
        public boolean hasNext() {
            return stack.isEmpty();
        }

        @Override
        public Integer next() {
            TreeNode nextNode = stack.pop();
            Integer ret = nextNode.val;
            nextNode = nextNode.right;
            while (nextNode != null) {
                stack.push(nextNode);
                nextNode = nextNode.left;
            }
            return ret;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode setLeft(int vLeft) {
        left = new TreeNode(vLeft);
        return left;
    }

    TreeNode setRight(int vRight) {
        right = new TreeNode(vRight);
        return right;
    }
}