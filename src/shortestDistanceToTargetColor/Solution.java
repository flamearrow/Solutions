package shortestDistanceToTargetColor;
//You are given an array colors, in which there are three colors: 1, 2 and 3.
//
//        You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.
//
//
//
//        Example 1:
//
//        Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
//        Output: [3,0,3]
//        Explanation:
//        The nearest 3 from index 1 is at index 4 (3 steps away).
//        The nearest 2 from index 2 is at index 2 itself (0 steps away).
//        The nearest 1 from index 6 is at index 3 (3 steps away).
//        Example 2:
//
//        Input: colors = [1,2], queries = [[0,3]]
//        Output: [-1]
//        Explanation: There is no 3 in the array.
//
//
//        Constraints:
//
//        1 <= colors.length <= 5*10^4
//        1 <= colors[i] <= 3
//        1 <= queries.length <= 5*10^4
//        queries[i].length == 2
//        0 <= queries[i][0] < colors.length
//        1 <= queries[i][1] <= 3

import java.util.*;

public class Solution {
    // use a DP table to save 1, 2, 3s closest to i
    // dp[i][0]: closet 1 to i
    // dp[i][1]: closet 2 to i
    // dp[i][2]: closet 3 to i
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int len = colors.length;
        int[][] dp = new int[len][3];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dp[0][colors[0] - 1] = 0;
        // looping from left to right, find closest numbers from left
        for (int i = 1; i < len; i++) {
            dp[i][colors[i] - 1] = 0;
            for (int c = 0; c < 3; c++) {
                if (c == colors[i] - 1) {
                    // dp[i][c] is already 0, no need to update
                    continue;
                }
                if (dp[i - 1][c] != Integer.MAX_VALUE) {
                    // [i-1]'s closest value to c is dp[i-1][c],
                    // then a possible candidate for dp[i][c] is dp[i-1][c] + 1
                    dp[i][c] = dp[i - 1][c] + 1;
                }
            }
        }

        dp[len - 1][colors[len - 1] - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            for (int c = 0; c < 3; c++) {
                if (c == colors[i] - 1) {
                    // dp[i][c] is already 0, no need to update
                    continue;
                }
                if (dp[i + 1][c] != Integer.MAX_VALUE) {
                    dp[i][c] = Math.min(dp[i][c], dp[i + 1][c] + 1);
                }
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int[] query : queries) {
            int result = dp[query[0]][query[1] - 1];
            results.add(result == Integer.MAX_VALUE ? -1 : result);
        }
        return results;
    }

    // Got TLE
    public List<Integer> shortestDistanceColorBinarySearch(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> colorIndicesMap = new HashMap<>();
        colorIndicesMap.put(1, new LinkedList<>());
        colorIndicesMap.put(2, new LinkedList<>());
        colorIndicesMap.put(3, new LinkedList<>());
        for (int i = 0; i < colors.length; i++) {
            colorIndicesMap.get(colors[i]).add(i);
        }

        int[] arr1 = new int[colorIndicesMap.get(1).size()];
        for (int i = 0; i < colorIndicesMap.get(1).size(); i++) {
            arr1[i] = colorIndicesMap.get(1).get(i);
        }
        int[] arr2 = new int[colorIndicesMap.get(2).size()];
        for (int i = 0; i < colorIndicesMap.get(2).size(); i++) {
            arr2[i] = colorIndicesMap.get(2).get(i);
        }
        int[] arr3 = new int[colorIndicesMap.get(3).size()];
        for (int i = 0; i < colorIndicesMap.get(3).size(); i++) {
            arr3[i] = colorIndicesMap.get(3).get(i);
        }


        List<Integer> results = new ArrayList<>();
        for (int[] query : queries) {
            int srcIdx = query[0];
            int color = query[1];
            switch (color) {
                case 1 -> addBinarySearchResult(arr1, srcIdx, results);
                case 2 -> addBinarySearchResult(arr2, srcIdx, results);
                case 3 -> addBinarySearchResult(arr3, srcIdx, results);
            }
        }
        return results;
    }

    void addBinarySearchResult(int[] array, int target, List<Integer> results) {
        if (array.length == 0) {
            results.add(-1);
        } else {
            int insertionPoint = Arrays.binarySearch(array, target);
            // if key is found, return key index,
            // otherwise return -insertionPoint-1, where insertionPoint is the first
            // point that's GREATER than target or the arr.length if it should be
            // inserted last
            // find a match in index, return 0
            if (insertionPoint < 0) {
                int realInsertionPoint = -insertionPoint - 1;
                if (realInsertionPoint == array.length) {
                    results.add(target - array[array.length - 1]);
                } else if (realInsertionPoint == 0) {
                    results.add(array[0] - target);
                } else {
                    results.add(Math.min(array[realInsertionPoint] - target,
                            target - array[realInsertionPoint - 1]));
                }
            }
            // if result >= 0, then it's guaranteed to be present in the array
            else {
                results.add(0);
            }
        }
    }


    public static void main(String[] args) {
        int[] colors = {1, 2};
        int[][] queries = {{0, 3}};
        for (int i : new Solution().shortestDistanceColorBinarySearch(colors, queries)) {
            System.out.println(i);
        }
    }
}