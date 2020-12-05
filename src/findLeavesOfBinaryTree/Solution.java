package findLeavesOfBinaryTree;

import treeUtils.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

//Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
//
//
//
//        Example:
//
//        Input: [1,2,3,4,5]
//
//          1
//         / \
//        2   3
//       / \
//      4   5
//
//        Output: [[4,5,3],[2],[1]]
//
//
//        Explanation:
//
//        1. Removing the leaves [4,5,3] would result in this tree:
//
//          1
//         /
//        2
//
//
//        2. Now removing the leaf [2] would result in this tree:
//
//        1
//
//
//        3. Now removing the leaf [1] would result in the empty tree:
//
//        []
//        [[3,5,4],[2],[1]], [[3,4,5],[2],[1]], etc, are also consider correct answers since per each level it doesn't matter the order on which elements are returned.
public class Solution {
    public List<List<Integer>> findLeavesTopologicalsort(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Set<TreeNode> nodes = new HashSet<>();
        doFill(root, nodes, parentMap);

        while (!nodes.isEmpty()) {
            Set<TreeNode> nodesWithoutChildren =
                    nodes.stream()
                            .filter(treeNode -> treeNode.left == null && treeNode.right == null
                            ).collect(Collectors.toSet());

            List<Integer> newResults = new ArrayList<>();
            for (TreeNode node : nodesWithoutChildren) {
                // the root of the tree doesn't have a parent
                if (parentMap.containsKey(node)) {
                    TreeNode parent = parentMap.get(node);
                    if (parent.left == node) {
                        parent.left = null;
                    } else if (parent.right == node) {
                        parent.right = null;
                    }
                }


                newResults.add(node.val);
            }
            ret.add(newResults);

            nodes.removeAll(nodesWithoutChildren);
        }
        return ret;

    }

    void doFill(TreeNode root, Set<TreeNode> nodes, Map<TreeNode, TreeNode> parentMap) {
        nodes.add(root);
        if (root.left != null) {
            parentMap.put(root.left, root);
            doFill(root.left, nodes, parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
            doFill(root.right, nodes, parentMap);
        }

    }


    // DFS to get the height of each node(bottom is 0, root is highest), then put them into result
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> heightNodeMap = new HashMap<>();
        probeHeight(root, heightNodeMap);

        List<List<Integer>> ret = new ArrayList<>(heightNodeMap.size());
        for (int i = 0; i < heightNodeMap.size(); i++) {
            ret.add(heightNodeMap.get(i));
        }
        return ret;
    }

    int probeHeight(TreeNode root, Map<Integer, List<Integer>> heightNodeMap) {
        if (root == null) {
            return -1;
        } else {
            int leftHeight = probeHeight(root.left, heightNodeMap);
            int rightHeight = probeHeight(root.right, heightNodeMap);
            int currentHeight = Math.max(leftHeight, rightHeight) + 1;
            if (!heightNodeMap.containsKey(currentHeight)) {
                heightNodeMap.put(currentHeight, new LinkedList<>());
            }
            heightNodeMap.get(currentHeight).add(root.val);
            return currentHeight;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, 2, 3);
        root.left.setLeft(4);
        root.left.setRight(5);

        for (List<Integer> ret : new Solution().findLeaves(root)) {
            ret.forEach(System.out::println);
        }
    }
}
