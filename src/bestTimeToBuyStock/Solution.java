package bestTimeToBuyStock;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
//and you can't short
public class Solution {
	// keep track of min we saw so far, update max for each number we see
	public int maxProfit(int[] prices) {
		if (prices.length == 0)
			return 0;
		int min = Integer.MAX_VALUE;
		int maxValue = 0;
		for (int price : prices) {
			if (price < min) {
				min = price;
			} else {
				if (price - min > maxValue) {
					maxValue = price - min;
				}
			}
		}
		return maxValue;
	}

	public static void main(String[] args) {
		int[] prices = { 1, 8, 3, 7 };
		System.out.println(new Solution().maxProfit(prices));
	}
}
