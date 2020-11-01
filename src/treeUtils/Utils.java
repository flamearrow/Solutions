package treeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Utils {
    //    [10,5,-3,3,2,null,11,3,-2,null,1]
    public static TreeNode createTree(Integer[] vals) {
        TreeNode root = new TreeNode(vals[0]);
        LinkedList<TreeNode> lastLayer = new LinkedList<>();
        lastLayer.add(root);
        int nextValIndex = 1;
        boolean left = true;
        while (nextValIndex < vals.length) {
            // get all last layer heads, find enough children this layer, create node, connect to
            // last layer, replace lastLayer with new nodes created
            TreeNode nextNode = vals[nextValIndex] == null ? null : new TreeNode(vals[nextValIndex]);
            // this is a left child
            if (left) {
                lastLayer.get(0).left = nextNode;
            }
            // this is a right child, pop head out
            else {
                lastLayer.removeFirst().right = nextNode;
            }

            if (nextNode != null) {
                lastLayer.addLast(nextNode);
            }

            left = !left;
            nextValIndex++;
        }
        return root;
    }

    static void printTreeInOrder(TreeNode root) {
        if (root != null) {
            printTreeInOrder(root.left);
            System.out.println(root.val);
            printTreeInOrder(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = createTree(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1});
        printTreeInOrder(root);
    }
}
