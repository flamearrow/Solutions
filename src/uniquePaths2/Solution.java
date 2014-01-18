package uniquePaths2;

//Follow up for "Unique Paths":
//
//Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
//An obstacle and empty space is marked as 1 and 0 respectively in the grid.
//
//For example,
//There is one obstacle in the middle of a 3x3 grid as illustrated below.
//
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//The total number of unique paths is 2.
//
//Note: m and n will be at most 100.
public class Solution {
	// another simple dp
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid.length == 0)
			return 0;
		int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
		boolean clear = false;
		for (int i = 0; i < obstacleGrid.length; i++) {
			if (!clear && obstacleGrid[i][0] == 0) {
				dp[i][0] = 1;
			} else {
				clear = true;
				dp[i][0] = 0;
			}
		}
		clear = false;
		for (int i = 0; i < obstacleGrid[0].length; i++) {
			if (!clear && obstacleGrid[0][i] == 0) {
				dp[0][i] = 1;
			} else {
				clear = true;
				dp[0][i] = 0;
			}
		}

		for (int i = 1; i < obstacleGrid.length; i++) {
			for (int j = 1; j < obstacleGrid[0].length; j++) {
				if (obstacleGrid[i][j] == 1) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}

		return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
	}

	public static void main(String[] args) {
		int[][] ob = {};
		System.out.println(new Solution().uniquePathsWithObstacles(ob));
	}
}
