package mostFrequentSubtreeSum;

import treeUtils.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
//
//        Examples 1
//        Input:
//
//         5
//        /  \
//        2   -3
//        return [2, -3, 4], since all the values happen only once, return all of them in any order.
//        Examples 2
//        Input:
//
//         5
//        /  \
//        2   -5
//        return [2], since 2 happens twice, however -5 only occur once.
//        Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
public class Solution {

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<TreeNode, Integer> treeSumMap = new HashMap<>();
        Map<Integer, Integer> sumFreqMap = new HashMap<>();
        treeSum(root, treeSumMap, sumFreqMap);

        List<Integer> mostFrequentSums = new LinkedList<>();
        int maxFrequency = 0;
        for (Map.Entry<Integer, Integer> entry : sumFreqMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentSums.clear();
                mostFrequentSums.add(entry.getKey());
            } else if (entry.getValue() == maxFrequency) {
                mostFrequentSums.add(entry.getKey());
            }
        }
        int[] ret = new int[mostFrequentSums.size()];
        for (int i = 0; i < mostFrequentSums.size(); i++) {
            ret[i] = mostFrequentSums.get(i);
        }
        return ret;
    }

    int treeSum(TreeNode root, Map<TreeNode, Integer> treeSumMap,
                Map<Integer, Integer> sumFreqMap) {
        if (root == null) {
            return 0;
        }
        if (treeSumMap.containsKey(root)) {
            return treeSumMap.get(root);
        }

        int treeSum = root.val + treeSum(root.left, treeSumMap, sumFreqMap) +
                treeSum(root.right, treeSumMap, sumFreqMap);
        treeSumMap.put(root, treeSum);

        int newFrequency;
        if (sumFreqMap.containsKey(treeSum)) {
            newFrequency = sumFreqMap.get(treeSum) + 1;
            sumFreqMap.put(treeSum, newFrequency);
        } else {
            newFrequency = 1;
            sumFreqMap.put(treeSum, newFrequency);
        }

        return treeSum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, 2, -5);
        for (int i : new Solution().findFrequentTreeSum(root)) {
            System.out.println(i);
        }
    }


}
