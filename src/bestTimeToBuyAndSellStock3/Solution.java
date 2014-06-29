package bestTimeToBuyAndSellStock3;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//Note:
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
public class Solution {

	public int maxProfit2(int[] prices) {
		if (prices.length == 0)
			return 0;
		int[] profitLR = new int[prices.length];
		int[] profitRL = new int[prices.length];
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
				profitLR[i] = profitLR[i - 1];
			} else {
				if (prices[i] - min > profitLR[i - 1]) {
					profitLR[i] = prices[i] - min;
				} else {
					profitLR[i] = profitLR[i - 1];
				}
			}
		}
		int max = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			if (prices[i] > max) {
				max = prices[i];
				profitRL[i] = profitRL[i + 1];
			} else {
				if (max - prices[i] > profitRL[i + 1]) {
					profitRL[i] = max - prices[i];
				} else {
					profitRL[i] = profitRL[i + 1];
				}
			}
		}
		int ret = 0;
		for (int i = 0; i < prices.length; i++) {
			if (profitLR[i] + profitRL[i] > ret) {
				ret = profitLR[i] + profitRL[i];
			}
		}
		return ret;
	}

	// the idea is to build two arrays similar to the one pair problem for O(n) each, then find the maximum
	// a more trivial way is to build the prefix array [0-j] in O(n), 
	//  then for each j, compute the same subproblem for [j+1, n-1]
	//  this would be a O(n^2) solution, but would be more general, 
	//  it can be used to solve 3 pair, 4 pair.. k pair problem in O(k^2)
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
