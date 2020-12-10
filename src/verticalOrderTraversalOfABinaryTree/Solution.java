package verticalOrderTraversalOfABinaryTree;
//Given a binary tree, return the vertical order traversal of its nodes values.
//
//        For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
//
//        Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
//
//        If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
//
//        Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
//
//
//
//        Example 1:
//
//
//
//        Input: [3,9,20,null,null,15,7]
//        Output: [[9],[3,15],[20],[7]]
//        Explanation:
//        Without loss of generality, we can assume the root node is at position (0, 0):
//        Then, the node with value 9 occurs at position (-1, -1);
//        The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
//        The node with value 20 occurs at position (1, -1);
//        The node with value 7 occurs at position (2, -2).
//        Example 2:
//
//
//
//        Input: [1,2,3,4,5,6,7]
//        Output: [[4],[2],[1,5,6],[3],[7]]
//        Explanation:
//        The node with value 5 and the node with value 6 have the same position according to the given scheme.
//        However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
//
//
//        Note:
//
//        The tree will have between 1 and 1000 nodes.
//        Each node's value will be between 0 and 1000.

import treeUtils.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, Map<Integer, List<TreeNode>>> xYNodesMap = new HashMap<>();
        // range[0]: minX
        // range[1]: maxX
        // range[2]: minY
        int[] range = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        probe(root, xYNodesMap, range, 0, 0);

        List<List<Integer>> ret = new LinkedList<>();

        for (int x = range[0]; x <= range[1]; x++) {
            if (xYNodesMap.containsKey(x)) {
                Map<Integer, List<TreeNode>> yMap = xYNodesMap.get(x);
                List<Integer> newNodesValues = new LinkedList<>();
                ret.add(newNodesValues);
                for (int y = 0; y >= range[2]; y--) {
                    if (yMap.containsKey(y)) {
                        List<TreeNode> nodes = yMap.get(y);
                        nodes.sort(Comparator.comparingInt(o -> o.val));
                        newNodesValues.addAll(nodes.stream().map(treeNode -> treeNode.val)
                                .collect(Collectors.toList()));
                    }
                }
            }
        }
        return ret;
    }

    void probe(TreeNode root, Map<Integer, Map<Integer, List<TreeNode>>> xYNodesMap, int[] range,
               int currentX, int currentY) {
        if (root == null) {
            return;
        }
        Map<Integer, List<TreeNode>> yMap;
        if (!xYNodesMap.containsKey(currentX)) {
            yMap = new HashMap<>();
            xYNodesMap.put(currentX, yMap);
        } else {
            yMap = xYNodesMap.get(currentX);
        }

        List<TreeNode> nodes;
        if (!yMap.containsKey(currentY)) {
            nodes = new ArrayList<>();
            yMap.put(currentY, nodes);
        } else {
            nodes = yMap.get(currentY);
        }
        nodes.add(root);


        // range[0]: minX
        // range[1]: maxX
        // range[2]: minY
        range[0] = Math.min(currentX, range[0]);
        range[1] = Math.max(currentX, range[1]);
        range[2] = Math.min(currentY, range[2]);

        probe(root.left, xYNodesMap, range, currentX - 1, currentY - 1);
        probe(root.right, xYNodesMap, range, currentX + 1, currentY - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, 2, 3);
        root.left.setLeft(4);
        root.left.setRight(5);

        root.right.setLeft(6);
        root.right.setRight(7);

        new Solution().verticalTraversal(root).forEach(list -> {
                    System.out.println();
                    list.forEach(System.out::print);
                }
        );
    }
}
