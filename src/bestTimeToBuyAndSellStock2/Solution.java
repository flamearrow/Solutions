package bestTimeToBuyAndSellStock2;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//
// note this is not a maximum sub array problem, you can complete as many transactions as you like
// so if you have 1 3 9, the max profit would be 3-1 + 9-3
public class Solution {
	public int maxProfit(int[] prices) {
		if (prices.length < 2)
			return 0;
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1])
				maxProfit += prices[i] - prices[i - 1];
		}
		return maxProfit;
	}
}
