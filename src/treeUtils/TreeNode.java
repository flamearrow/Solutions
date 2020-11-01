package treeUtils;

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

    public TreeNode setLeft(int val) {
        this.left = new TreeNode(val, null, null);
        return this.left;
    }

    public TreeNode setRight(int val) {
        this.right = new TreeNode(val, null, null);
        return this.right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + (left == null ? "null" : left.val) +
                ", right=" + (right == null ? "null" : right.val) +
                '}';
    }
}
