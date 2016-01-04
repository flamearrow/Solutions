package perfectSqrs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
// which sum to n.
//
// For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(13));
    }

    public int numSquares(int n) {
        // dp[i] is the result for i
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int sqr : getSqrsSmallerThan(i)) {
                dp[i] = Math.min(dp[i], dp[i - sqr] + 1);
            }
        }
        return dp[n];
    }

    List<Integer> getSqrsSmallerThan(int i) {
        List<Integer> ret = new LinkedList<>();
        int prob = 1;
        while (prob * prob <= i) {
            ret.add(prob * prob);
            prob++;
        }
        return ret;
    }
}
