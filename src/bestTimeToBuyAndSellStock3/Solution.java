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
		int[] diff = new int[prices.length - 1];
		for (int i = 1; i < prices.length; i++) {
			diff[i - 1] = prices[i] - prices[i - 1];
		}

		// find the first two max subarray of diff
		int max = 0;
		int secondMax = 0;
		int maxE = -1;

		int start = 0, end = 1;
		int currentSum = diff[0];
		while (start < diff.length) {
			// might hit new max
			if (currentSum > max) {
				// new max doesn't intersect with old max, move old max as
				// second max
				// if new max intersects with old max, we just replace old max
				// with new max, don't touch second max
				if (start >= maxE) {
					secondMax = max;
				}
				max = currentSum;
				maxE = end;
			} else if (currentSum > secondMax) {
				// new second max doesn't intersect with max, we replace second
				// max
				// if new second max intersects with max, then we drop new
				// second max
				if (start >= maxE) {
					secondMax = currentSum;
				}
			}
			if (currentSum >= 0 && end < diff.length) {
				currentSum += diff[end];
				end++;
			} else {
				currentSum -= diff[start];
				start++;
			}
		}
		return max + secondMax;
	}

	public static void main(String[] args) {
		int[] prices = { 6, 1, 3, 2, 4, 7 };
		System.out.println(new Solution().maxProfit(prices));
	}
}
