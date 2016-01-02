package bestTimeToBuyAndSellStockWithCoolDown;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//Example:
//
//prices = [1, 2, 3, 0, 2]
//maxProfit = 3
//transactions = [buy, sell, cooldown, buy, sell]
public class Solution {
	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 4, 5 };
		System.out.println(new Solution().maxProfit(prices));
	}

	// two DP arrays
	// dp1: profit made if I sell on day i
	// dp2: profit made if I do nothing on day i
	public int maxProfit2(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int[] dp1 = new int[prices.length];
		int[] dp2 = new int[prices.length];
		for (int i = 1; i < prices.length; i++) {
			// if we sell on day i, the profile could be
			//  1: we sold on day i-1, and need to buy on i-1 again and sell on i
			//     this essentially covers the situation that we buy on some day and hold
			//     for multiple days
			//		dp1[i-1] + prices[i] - prices[i-1]
			//  2: we did nothing on day i-1, so we need to buy on i and sell on i
			//		dp2[i-1]
			dp1[i] = Math.max(dp1[i - 1] + prices[i] - prices[i - 1], dp2[i - 1]);
			dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
		}
		return Math.max(dp1[prices.length - 1], dp2[prices.length - 1]);
	}

	int[][] buf;

	// divide and conquer
	public int maxProfit(int[] prices) {
		buf = new int[prices.length][prices.length];
		return doShit(prices, 0, prices.length - 1);
	}

	int doShit(int[] prices, int start, int end) {
		if (start < 0 || start >= prices.length || end < 0 || end >= prices.length) {
			return 0;
		}
		if (buf[start][end] > 0) {
			return buf[start][end];
		}
		if (end <= start) {
			return 0;
		}

		// don't divide
		int ret = prices[end] > prices[start] ? prices[end] - prices[start] : 0;
		// divide at each point
		for (int i = start; i <= end; i++)

		{
			ret = Math.max(ret, doShit(prices, start, i - 1) + doShit(prices, i + 1, end));
		}
		buf[start][end] = ret;
		return ret;
	}
}
