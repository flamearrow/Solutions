package addToAPrime;

import java.util.Arrays;

// Given a set of numbers [1-N], find the number of subsets such that sum of subset is a prime
public class Solution {

	// Using current to predict future values:
	// dp[i][j]:  
	// 	if not use j, we have dp[i-1][j] ways
	//  if use j, we contribute to dp[i][j+i] A ways where A = dp[i][j-1]
	static int getNumSubsets2(int n) {
		int max = (1 + n) * n / 2;
		boolean[] primeMask = createPrimeMask(max);
		// dp[i][j] is the number of subsets that added to j for the first i numbers
		int[][] dp = new int[n + 1][max + 1];
		// for first number 1, the only possible sum is 1, and there's only 1 subset
		dp[1][1] = 1;
		// to fill in the dp table:
		//  1) a number i can add to it self: dp[i][i] += 1
		//  2) not adding number i, get from the result of last row: dp[i][sum] += dp[i-1][sum]
		//  3) adding number i, modify result from last row: dp[i][sum+i] += dp[i-1][sum]
		for (int i = 2; i <= n; i++) {
			// the number i can add to a sum i - we add a new combination
			dp[i][i] += 1;
			for (int sum = 1; sum <= max - i; sum++) {
				// not adding i, the sum would be sum
				dp[i][sum] += dp[i - 1][sum];
				// adding i, the sum would be sum+i
				dp[i][sum + i] += dp[i - 1][sum];
			}
		}

		// now dp[n][1-max] would contain the number of subsets that first n numbers add to [1-max]
		int ret = 0;
		for (int k = 2; k <= max; k++) {
			if (primeMask[k]) {
				ret += dp[n][k];
			}
		}
		return ret;
	}

	static boolean[] createPrimeMask(int max) {
		boolean[] ret = new boolean[max + 1];
		Arrays.fill(ret, true);
		int cur = 2;
		while (cur < ret.length) {
			for (int i = cur * cur; i < ret.length; i += cur) {
				ret[i] = false;
			}
			cur++;
			while (cur < ret.length && !ret[cur]) {
				cur++;
			}
		}
		return ret;
	}

	// dp[i][j] means the number of ways to add to number j in first i numbers
	// after filling out dp table, we need to go through dp[n][0-maxSum] for all dp[n][mlgb] where mlgb is prime 
	// we add the number of ways

	// Using previous to calculate current:
	// dp[i][j]:
	//	if not use i, we have dp[i-1][j] ways ... 1
	//  if use i, we have dp[i-1][j-i] ways   ... 2
	// add 1 and 2
	// need to check j>=i, if not then just consider 1
	// this is similar to knapsack problem
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
		System.out.println(getNumSubsets(3));
		System.out.println(getNumSubsets2(3));
	}

}
