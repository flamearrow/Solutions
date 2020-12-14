package treeUtils;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;


    public TreeNode(int val) {
        this(val, null, null);
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val, int leftVal, int rightVal) {
        this(val, new TreeNode(leftVal), new TreeNode(rightVal));
    }

    /**
     * returns left
     */
    public TreeNode setLeft(int val) {
        this.left = new TreeNode(val, null, null);
        return this.left;
    }

    public TreeNode setLeftReturnSelf(int val) {
        this.left = new TreeNode(val, null, null);
        return this;
    }

    /**
     * returns right
     */
    public TreeNode setRight(int val) {
        this.right = new TreeNode(val, null, null);
        return this.right;
    }

    public TreeNode setRightReturnSelf(int val) {
        this.right = new TreeNode(val, null, null);
        return this;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + (left == null ? "null" : left.val) +
                ", right=" + (right == null ? "null" : right.val) +
                '}';
    }

    public void printTreeRootByLevel() {
        TreeNode root = this;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int nodesLeftAtCurrentLevel = 1;
        int level = 0;
        System.out.println("level 0");
        while (!queue.isEmpty()) {
            TreeNode next = queue.removeFirst();
            System.out.print(next.val + " ");

            if (next.left != null) {
                queue.addLast(next.left);
            }
            if (next.right != null) {
                queue.addLast(next.right);
            }

            nodesLeftAtCurrentLevel--;
            if (nodesLeftAtCurrentLevel == 0) {
                level++;
                nodesLeftAtCurrentLevel = queue.size();
                if(nodesLeftAtCurrentLevel > 0) {
                    System.out.println("\n\nlevel " + level);
                }
            }
        }

    }
}
