package minimumPathSum;

//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//
//Note: You can only move either down or right at any point in time.
public class Solution {
	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(new Solution().minPathSum2(grid));
	}

	public int minPathSum2(int[][] grid) {
		int width = grid[0].length;
		int height = grid.length;
		int[][] dp = new int[height][width];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < width; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}
		for (int i = 1; i < height; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				dp[i][j] = (dp[i - 1][j] < dp[i][j - 1] ? dp[i - 1][j]
						: dp[i][j - 1]) + grid[i][j];
			}
		}
		return dp[height - 1][width - 1];
	}

	public int minPathSum(int[][] grid) {
		int yLen = grid.length;
		int xLen = grid[0].length;
		// dp[i][j] is the min sum from 00 to ij
		int dp[][] = new int[yLen][xLen];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < xLen; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}
		for (int i = 1; i < yLen; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int y = 1; y < yLen; y++) {
			for (int x = 1; x < xLen; x++) {
				dp[y][x] = dp[y - 1][x] > dp[y][x - 1] ? dp[y][x - 1]
						+ grid[y][x] : dp[y - 1][x] + grid[y][x];
			}
		}
		return dp[yLen - 1][xLen - 1];
	}
}
