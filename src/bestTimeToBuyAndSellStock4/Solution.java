package bestTimeToBuyAndSellStock4;

// you are allowed to up to k transactions
public class Solution {

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if (n < 2 || k == 0) {
			return 0;
		}
		// if k >= n/2, then you can make maximum number of transactions.
		if (k >= n / 2) {
			int maxPro = 0;
			for (int i = 1; i < n; i++) {
				if (prices[i] > prices[i - 1])
					maxPro += prices[i] - prices[i - 1];
			}
			return maxPro;
		}
		int[][] dp = new int[n][k];
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < k; j++) {
				// not sell at i
				dp[i][j] = dp[i - 1][j];
				for (int buyAt = 0; buyAt < i; buyAt++) {
					// use buyAt not buyAt-1 because we can buy at buyAt and sell today
					dp[i][j] = Math.max(dp[i][j], prices[i] - prices[buyAt] + dp[buyAt][j]);
				}
			}
		}
		return dp[n - 1][k - 1];
	}

}
