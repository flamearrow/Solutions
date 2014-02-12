package addToAPrime;

// Given a set of numbers [1-N], find the number of subsets such that sum of subset is a prime
public class Solution {
	// dp[i][j] means the number of ways to add to number j in first i numbers
	// after filling out dp table, we need to go through dp[n][0-maxSum] for all dp[n][mlgb] where mlgb is prime 
	// we add the number of ways
	static int getNumSubsets(int n) {
		int maxSum = (n + 1) * n / 2;
		// cross off all non prime from 0 to maxSum+1
		boolean[] nonPrimes = new boolean[maxSum + 1];
		for (int i = 2; i < maxSum + 1; i++) {
			if (nonPrimes[i])
				continue;
			int cross = i * i;
			while (cross < maxSum + 1) {
				nonPrimes[cross] = true;
				cross += i;
			}
		}

		int[][] dp = new int[n + 1][maxSum + 1];
		dp[1][1] = 1;
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}
		for (int i = 2; i < n + 1; i++) {
			int max = i * (1 + i) / 2;
			for (int j = 1; j < max + 1; j++) {
				if (j - i >= 0) {
					// dp[i-1][j] : # of ways where first i-1 numbers can add to j, don't use i
					// dp[i-1][j-1] : # of ways where first i-1 numbers can add to j-i, use i, add i to it and get j
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - i];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		// use the nonPrimes array to expediate checking
		int ret = 0;
		for (int i = 2; i <= maxSum; i++) {
			if (isPrime(i, nonPrimes))
				ret += dp[n][i];

		}
		return ret;
	}

	static boolean isPrime(int num, boolean[] nonPrimes) {
		// actually we can check from 2 to Math.sqrt(num)
		int roof = (int) Math.sqrt(num);
		for (int i = 2; i <= roof; i++) {
			if (nonPrimes[i])
				continue;
			else {
				if (num % i == 0)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(getNumSubsets(30));
	}

}
