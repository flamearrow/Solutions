package bestTimeToBuyAndSellStock3;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//Note:
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
public class Solution {
	public int maxProfit(int[] prices) {
		if (prices.length < 2)
			return 0;
		int len = prices.length;
		int[] preProfit = new int[len];
		int[] postProfit = new int[len];

		int preSmall = prices[0];
		int postBig = prices[len - 1];
		int maxProfit = 0;
		for (int i = 1; i < len; i++) {
			// find a new small value, all the right high value can be applied
			// to this small value
			if (preSmall > prices[i]) {
				preSmall = prices[i];
			}
			// try to use this value to minus smallest value we see so
			// far and see if we have a bigger profit
			// if not, use the previous value int preProfit
			int newProfit = prices[i] - preSmall;
			preProfit[i] = preProfit[i - 1] > newProfit ? preProfit[i - 1]
					: newProfit;
		}

		for (int i = len - 2; i >= 0; i--) {
			// find a new big value, all the left small value can be applied to
			// this big value
			if (postBig < prices[i]) {
				postBig = prices[i];
			}
			int newProfit = postBig - prices[i];
			postProfit[i] = postProfit[i + 1] > newProfit ? postProfit[i + 1]
					: newProfit;
			if (preProfit[i] + postProfit[i] > maxProfit) {
				maxProfit = preProfit[i] + postProfit[i];
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		int[] prices = { 6, 1, 3, 2, 4, 7 };
		System.out.println(new Solution().maxProfit(prices));
	}
}
