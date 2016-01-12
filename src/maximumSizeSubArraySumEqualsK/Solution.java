package maximumSizeSubArraySumEqualsK;

import java.util.HashMap;
import java.util.Map;

//Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
// If there isn't one, return 0 instead.
//
//        Example 1:
//        Given nums = [1, -1, 5, -2, 3], k = 3,
//        return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
//
//        Example 2:
//        Given nums = [-2, -1, 2, 1], k = 1,
//        return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
//
//        Follow Up:
//        Can you do it in O(n) time?
public class Solution {
    public static void main(String[] args) {
        int[] arr = {-2, -1, 2, 1};
        int k = 1;
        System.out.println(new Solution().maxSubArrayLen(arr, k));
    }

    // the idea is to have a Map to map [(sum from 0 to i), i]
    //  keep a current sum that sums up from 0 to current
    //  each time a new number is added, try to find if there's key (curSum-k) exists
    //   if true, then we have a sub array from map.get(curSum-k) to currentNode that sums to k
    // then we put (currentSum, i) to map, note we only put the leftmost i to make sure it's longest
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // sum(0 to i) is sum
            //  we need find some j such that sum(0 to j) is k-sum, so that sum(j to i) is k
            if (sum == k) {
                maxLen = i + 1;
            }
            int residue = sum - k;
            if (indexMap.containsKey(residue)) {
                int newLen = i - indexMap.get(residue);
                maxLen = Math.max(maxLen, newLen);
            }
            if (!indexMap.containsKey(sum)) {
                indexMap.put(sum, i);
            }
        }
        return maxLen;
    }
}
