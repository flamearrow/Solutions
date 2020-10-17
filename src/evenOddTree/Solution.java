package evenOddTree;

//A binary tree is named Even-Odd if it meets the following conditions:
//
//        The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
//        For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
//        For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).


import java.util.LinkedList;

class TreeNode {
    int val;

    public TreeNode setLeft(int val) {
        this.left = new TreeNode(val);
        return this;
    }

    public TreeNode setRight(int val) {
        this.right = new TreeNode(val);
        return this;
    }

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

    @Override
    public String toString() {
        return "" + val;
    }
}

public class Solution {

    public static void bfsLvl(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int numOfNodesAtCurrentLvl = 1;
        int lvl = 0;

        while (!queue.isEmpty()) {
            System.out.println("current lvl: " + lvl);
            TreeNode next = queue.removeLast();
            System.out.println(next.val);
            if (next.left != null) {
                queue.addFirst(next.left);
            }
            if (next.right != null) {
                queue.addFirst(next.right);
            }
            // update lvl
            if (--numOfNodesAtCurrentLvl == 0) {
                lvl++;
                numOfNodesAtCurrentLvl = queue.size();
            }
        }


    }


    public boolean isEvenOddTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);

        int lvl = 0;
        int lastValue = Integer.MIN_VALUE;
        int numOfNodesAtCurrentLvl = 1;
        while (!queue.isEmpty()) {
            TreeNode next = queue.removeLast();
            System.out.println("touching " + next.val);
            // even
            if (lvl % 2 == 0) {
                if (next.val <= lastValue || next.val % 2 == 0) {
                    return false;
                }
                lastValue = next.val;
            }
            // odd
            else {
                if (next.val >= lastValue || next.val % 2 == 1) {
                    return false;
                }
                lastValue = next.val;
            }

            if (next.left != null) {
                queue.addFirst(next.left);
            }
            if (next.right != null) {
                queue.addFirst(next.right);
            }
            // lvl end
            if (--numOfNodesAtCurrentLvl == 0) {
                lvl++;
                lastValue = lvl % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                numOfNodesAtCurrentLvl = queue.size();
            }
        }
        return true;

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.setLeft(20).setRight(18);
        root.left.setLeft(7).setRight(9);
        root.left.right.setLeft(18);
        root.left.right.left.setLeft(7);

        System.out.println(new Solution().isEvenOddTree(root));
//        bfsLvl(root);
    }

}
