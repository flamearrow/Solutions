package dp.maxCut;

// Given a rope of integer lengths, cut the rope into pieces with integer lengths
// such that the product of all parts is maximized
public class Solution {
	// just try all possible last part, initialize dp[0] = 1 for a neat iteration
	static int max(int ropeLen) {
		int[] dp = new int[ropeLen + 1];
		dp[0] = 1;
		for (int i = 1; i <= ropeLen; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				max = Math.max(max, dp[j] * (i - j));
			}
			dp[i] = max;
		}
		return dp[ropeLen];
	}

	public static void main(String[] args) {
		System.out.println(max(10));
	}
}
