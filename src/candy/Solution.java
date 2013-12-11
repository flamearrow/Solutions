package candy;

//There are N children standing in a line. Each child is assigned a rating value.
//
//You are giving candies to these children subjected to the following requirements:
//
//   Each child must have at least one candy.
//   Children with a higher rating get more candies than their neighbors.
//
//What is the minimum candies you must give? 
public class Solution {
	public int candy(int[] ratings) {
		int[] dp = new int[ratings.length];
		dp[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				dp[i] = dp[i - 1] + 1;
			} else {
				int k = i;
				dp[k] = 1;
				while (k > 0 && ratings[k] < ratings[k-1] && dp[k] == dp[k - 1]) {
					dp[k - 1]++;
					k--;
				}
			}
		}
		int min = 0;
		for (int i : dp) {
			min += i;
		}
		return min;
	}

	public static void main(String[] args) {
		int[] ratings = { 2,2 };
		System.out.println(new Solution().candy(ratings));
	}
}
