package uniquePaths;

//A robot is located at the top-left corner of a m x n grid 
//
//The robot can only move either down or right at any point in time. 
//
//The robot is trying to reach the bottom-right corner of the grid 
//
//How many possible unique paths are there?

//Note: m and n will be at most 100
public class Solution {
	// a simple dp
	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 1;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < n; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		return dp[m - 1][n - 1];
	}

	public int uniquePathsFac(int m, int n) {
		if (m <= 1 || n <= 1)
			return 1;
		return facN(m + n - 2) / (facN(m - 1) * facN(n - 1));
	}

	static int facN(int n) {
		if (n == 1)
			return 1;
		else
			return n * facN(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(new Solution().uniquePathsFac(1, 2));

	}
}
