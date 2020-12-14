package serializeAndDeserializeBST;

import treeUtils.TreeNode;

//Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
//        Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
//
//        The encoded string should be as compact as possible.
//
//
//
//        Example 1:
//
//        Input: root = [2,1,3]
//        Output: [2,1,3]
//        Example 2:
//
//        Input: root = []
//        Output: []
//
//
//        Constraints:
//
//        The number of nodes in the tree is in the range [0, 104].
//        0 <= Node.val <= 104
//        The input tree is guaranteed to be a binary search tree.
public class Codec {
    private static final String SEPARATOR = " ";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString();
    }

    void preOrderTraversal(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        sb.append(SEPARATOR);
        preOrderTraversal(root.left, sb);
        preOrderTraversal(root.right, sb);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] values = data.split(SEPARATOR);
        return createBST(values, 0, values.length);
    }

    // [left, right)
    TreeNode createBST(String[] values, int left, int right) {
        if (left >= right) {
            return null;
        }
        int rootValue = Integer.parseInt(values[left]);
        TreeNode root = new TreeNode(rootValue);
        for (int boundary = left + 1; boundary < right; boundary++) {
            int boundaryValue = Integer.parseInt(values[boundary]);
            if (boundaryValue > rootValue) {
                // [left+1, boundary-1] is left child
                root.left = createBST(values, left + 1, boundary);
                // [boundary, right-1] is right child
                root.right = createBST(values, boundary, right);
                return root;
            }
        }
        // all nodes in the range is smaller than root
        root.left = createBST(values, left + 1, right);
        return root;
    }
}
