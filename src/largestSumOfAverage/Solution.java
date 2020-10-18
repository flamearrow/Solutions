package largestSumOfAverage;

//We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
//
//        Note that our partition must use every number in A, and that scores are not necessarily integers.
//
//        Example:
//        Input:
//        A = [9,1,2,3,9]
//        K = 3
//        Output: 20
//        Explanation:
//        The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
//        We could have also partitioned A into [9, 1], [2], [3, 9], for example.
//        That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
//
//
//        Note:
//
//        1 <= A.length <= 100.
//        1 <= A[i] <= 10000.
//        1 <= K <= A.length.
//        Answers within 10^-6 of the correct answer will be accepted as correct.

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        return findAvg(A, A.length, K);
    }

    double findAvg(int[] A, int length, int groups) {
        if (length < groups) {
            return 0;
        }
        if (groups == 1) {
            int sum = 0;
            for (int i = 0; i < length; i++) {
                sum += A[i];
            }
            return (double) sum / length;
        } else {
            double max = 0;
            int cur = 0;
            for (int rightMost = length - 1; rightMost >= 0; rightMost--) {
                cur += A[rightMost];
                double previousAvg = findAvg(A, rightMost, groups - 1);
                max = Math.max(previousAvg + (double) cur / (length - rightMost), max);
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] input = {9, 1, 2, 3, 9};
        int groups = 3;
        System.out.println(new Solution().largestSumOfAverages(input, 3));
    }
}
