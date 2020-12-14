package serializeAndDeserializeBinaryTree;

import treeUtils.TreeNode;

import java.util.LinkedList;

//Serialization is the process of converting a data structure or object into
// a sequence of bits so that it can be stored in a file or memory buffer,
// or transmitted across a network connection link to be reconstructed later
// in the same or another computer environment.
//
//Design an algorithm to serialize and deserialize a binary tree.
// There is no restriction on how your serialization/deserialization algorithm should work.
// You just need to ensure that a binary tree can be serialized to a string and this string
// can be deserialized to the original tree structure.
//
//For example, you may serialize the following tree
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
// You do not necessarily need to follow this format, so please be creative and come up
// with different approaches yourself.
//Note: Do not use class member/global/static variables to store states.
// Your serialize and deserialize algorithms should be stateless.
public class Codec2 {
    private static final String NULL_NODE = "X";
    private static final String SPLIT = " ";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return NULL_NODE;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode next = queue.removeFirst();
            if (next != null) {
                sb.append(next.val);
                sb.append(SPLIT);
                queue.addLast(next.left);
                queue.addLast(next.right);
            } else {
                sb.append(NULL_NODE);
                sb.append(SPLIT);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(NULL_NODE)) {
            return null;
        }
        TreeNode root = null;
        boolean firstNode = true;
        boolean isLeftChild = true;

        LinkedList<TreeNode> parents = new LinkedList<>();
        for (String nextToken : data.split(SPLIT)) {
            if (firstNode) {
                root = new TreeNode(Integer.parseInt(nextToken));
                parents.addLast(root);
                firstNode = false;
            } else {
                if (isLeftChild) {
                    if (nextToken.equals(NULL_NODE)) {
                        parents.getFirst().left = null;
                    } else {
                        TreeNode leftChild = new TreeNode(Integer.parseInt(nextToken));
                        parents.getFirst().left = leftChild;
                        parents.addLast(leftChild);
                    }
                    isLeftChild = false;
                } else {
                    if (nextToken.equals(NULL_NODE)) {
                        parents.removeFirst().right = null;
                    } else {
                        TreeNode rightChild = new TreeNode(Integer.parseInt(nextToken));
                        parents.removeFirst().right = rightChild;
                        parents.addLast(rightChild);
                    }
                    isLeftChild = true;
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, 2, 3);
        root.setLeft(2);
        root.setRight(3).setLeftReturnSelf(4).setRight(5);

        String serialized = new Codec2().serialize(root);

        TreeNode deserialized = new Codec2().deserialize(serialized);

        deserialized.printTreeRootByLevel();


    }


}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));