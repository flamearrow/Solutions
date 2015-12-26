package maxSquare;

//Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
//
//For example, given the following matrix:
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//Return 4.
public class Solution {
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int height = matrix.length;
		int width = matrix[0].length;
		int[][] dp = new int[height][width];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = matrix[i][j] - '0';
				} else if (matrix[i][j] == '0') {
					dp[i][j] = 0;
				} else {
					// the idea is to find the min edge ended at its upper left
					// corners
					// if we find min and give matrix[i][j] == 1, we're bound to
					// create a sqr with edge == min+1
					dp[i][j] = Math.min(dp[i - 1][j],
							Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
				}
				if (dp[i][j] > max) {
					max = dp[i][j];
				}
			}
		}
		return max * max;
	}
}
