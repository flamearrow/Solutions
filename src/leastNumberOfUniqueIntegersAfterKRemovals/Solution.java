package leastNumberOfUniqueIntegersAfterKRemovals;
//Given an array of integers arr and an integer k.
// Find the least number of unique integers after removing exactly k elements.
//
//
//
//        Example 1:
//
//        Input: arr = [5,5,4], k = 1
//        Output: 1
//        Explanation: Remove the single 4, only 5 is left.
//        Example 2:
//        Input: arr = [4,3,1,1,3,3,2], k = 3
//        Output: 2
//        Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
//
//
//        Constraints:
//
//        1 <= arr.length <= 10^5
//        1 <= arr[i] <= 10^9
//        0 <= k <= arr.length

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // 2 2 1 1 1 -> 2 3
        int[] sortedGroupNums = getGroups(arr);
        int groupIndex = 0;
        while (k > 0) {
            k -= sortedGroupNums[groupIndex];
            groupIndex++;
        }

        if (k == 0) {
            // left over numbers: from groupIndex to end
            return sortedGroupNums.length - groupIndex;
        } else { // k < 0
            // left over numbers: from groupIndex-1 to end
            return sortedGroupNums.length - groupIndex + 1;
        }
    }

    int[] getGroups(int[] arr) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i : arr) {
            if (numCount.containsKey(i)) {
                numCount.put(i, numCount.get(i) + 1);
            } else {
                numCount.put(i, 1);
            }
        }
        int[] ret = new int[numCount.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            ret[i] = entry.getValue();
            i++;
        }

        Arrays.sort(ret);
        return ret;
    }

    public static void main(String[] args) {
        int[] input = {4,3,1,1,3,3,2};
        int k = 3;
        System.out.println(new Solution().findLeastNumOfUniqueInts(input, k));
//        for (int group : new Solution().findLeastNumOfUniqueInts(input, k)) {
//            System.out.println(group);
//        }
    }


}
