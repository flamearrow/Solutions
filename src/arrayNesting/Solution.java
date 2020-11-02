package arrayNesting;
//A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the
// longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.
//
//        Suppose the first element in S starts with the selection of element A[i] of index = i, the
//        next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding
//        right before a duplicate element occurs in S.
//
//
//
//        Example 1:
//
//        Input: A = [5,4,0,3,1,6,2]
//        Output: 4
//        Explanation:
//        A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
//
//        One of the longest S[K]:
//        S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
//
//
//        Note:
//
//        N is an integer within the range [1, 20,000].
//        The elements of A are all distinct.
//        Each element of A is an integer within the range [0, N-1].

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int arrayNestingBool(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            int curLen;
            if (i == nums[i]) {
                curLen = 1;
            } else {
                curLen = 2;
                int curentIndex = nums[i];
                while (i != nums[curentIndex]) {
                    visited[curentIndex] = true;
                    curentIndex = nums[curentIndex];
                    curLen++;
                }
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }

    public int arrayNesting(int[] nums) {
        int[] len = new int[nums.length];
        Arrays.fill(len, -1);

        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int length = -1;
            if (len[i] >= 0) {
                length = len[i];
            } else {
                length = prob(nums, i, len);
            }
            maxLen = Math.max(length, maxLen);
        }
        return maxLen;
    }

    int prob(int[] nums, int index, int[] len) {
        if (index == nums[index]) {
            len[index] = 1;
            return 1;
        }
        Set<Integer> visited = new HashSet<>();
        // first find how long
        while (!visited.contains(index)) {
            visited.add(index);
            index = nums[index];
        }

        // then update the length along the root
        int length = visited.size();
        visited.clear();
        while (!visited.contains(index)) {
            visited.add(index);
            len[index] = length;
            length--;
            index = nums[index];
        }

        return visited.size();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().arrayNestingBool(new int[]{5, 4, 0, 3, 1, 6, 2}));
    }
}
