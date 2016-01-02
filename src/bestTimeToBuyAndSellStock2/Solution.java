package bestTimeToBuyAndSellStock2;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//
// note this is not a maximum sub array problem, you can complete as many transactions as you like
// so if you have 1 3 9, the max profit would be 3-1 + 9-3
//
// a slightly different problem: you're not allowed to sell and buy on the same day
// so for 1,3,9, the maximum profit would be 9-3 = 6
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

	// not allowed to buy and sell on the same day
	// local min and local max: 
	//  if a[i-1]<=a[i]>a[i+1] then it's local max
	//  if a[i-1]>a[i]<=a[i+1] then it's local min
	//  we need to buy at local min and sell at local max
	// when we can do as many as we can, make sure to buy at bottom and sell at peak
	public int maxProfit2(int[] prices) {
		int len = prices.length;
		int buy = prices[0];
		int ret = 0;
		for (int i = 1; i < len - 1; i++) {
			// local maximum, need to sell
			if (prices[i] >= prices[i - 1] && prices[i] > prices[i + 1]) {
				ret += prices[i] - buy;
				buy = prices[i + 1];
			}
			// local minimum, need to buy
			else if (prices[i] <= prices[i + 1] && prices[i - 1] > prices[i]) {
				buy = prices[i];
			}
		}
		// we haven't consider the last one, need to take into account
		if (prices[len - 1] > buy) {
			ret += prices[len - 1] - buy;
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 1, 3, 2, 2, 5 };
		System.out.println(new Solution().maxProfit2(arr));
	}
}
