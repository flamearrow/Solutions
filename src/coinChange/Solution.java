package coinChange;

import java.util.Arrays;

//You are given coins of different denominations and a total amount of money amount. 
// Write a function to compute the fewest number of coins that you need to make up that amount. 
// If that amount of money cannot be made up by any combination of the coins, return -1.
//
//Example 1:
//coins = [1, 2, 5], amount = 11
//return 3 (11 = 5 + 5 + 1)
//
//Example 2:
//coins = [2], amount = 3
//return -1.
//
//Note:
//You may assume that you have an infinite number of each kind of coin.
public class Solution {
	public static void main(String[] args) {
		int[] coins = { 1 };
		System.out.println(new Solution().coinChangeDp(coins, 1));
	}

	public int coinChangeDp(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		int[] dp = new int[amount + 1];
		dp[0] = 0;
		Arrays.fill(dp, Integer.MAX_VALUE);
		for (int coin : coins) {
			if (coin < dp.length) {
				dp[coin] = 1;
			}
		}
		for (int i = 0; i <= amount; i++) {
			for (int coin : coins) {
				if (i - coin >= 0 && i - coin < amount
						&& dp[i - coin] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}

		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}

	public int coinChange(int[] coins, int amount) {
		int rst = doShit(coins, amount, Integer.MAX_VALUE, 0);
		return rst == Integer.MAX_VALUE ? -1 : rst;
	}

	int doShit(int[] coins, int left, int min, int cur) {
		if (left == 0) {
			return Math.min(min, cur);
		}
		if (cur >= min) {
			return min;
		}
		for (int coin : coins) {
			if (coin <= left) {
				min = Math.min(min, doShit(coins, left - coin, min, cur + 1));
			}
		}
		return min;
	}
}
