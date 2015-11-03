package longestIncreasingSequence;

//Find the longest increasing(increasing means one step) sequence in an integer matrix in 4 directions (up down left right), return the sequence
//For Example:
//[18273645]
//
//The output should be [1, 2, 3, 4, 5, 6, 7, 8]
public class LongestIncreasingSequence {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 8, 7, 6, 5 } };
		new LongestIncreasingSequence().findSeq(matrix);
	}

	void findSeq(int[][] matrix) {
		int height = matrix.length;
		int width = matrix[0].length;
		int[][] dp = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				dp[i][j] = -1;
			}
		}
		int len = Integer.MIN_VALUE;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int curLen = probe(matrix, dp, i, j, Integer.MIN_VALUE);
				len = Math.max(len, curLen);
			}
		}
		System.out.println(len);
	}

	int probe(int[][] matrix, int[][] dp, int i, int j, int pre) {
		int height = matrix.length;
		int width = matrix[0].length;
		if (i < 0 || i >= height || j < 0 || j >= width) {
			return -1;
		}
		if (pre > matrix[i][j]) {
			return -1;
		}
		if (dp[i][j] < 0) {
			int maxLen = Integer.MIN_VALUE;
			int head = matrix[i][j];
			int down = probe(matrix, dp, i + 1, j, head);
			maxLen = Math.max(down, maxLen);
			int up = probe(matrix, dp, i - 1, j, head);
			maxLen = Math.max(up, maxLen);
			int left = probe(matrix, dp, i, j - 1, head);
			maxLen = Math.max(left, maxLen);
			int right = probe(matrix, dp, i, j + 1, head);
			maxLen = Math.max(right, maxLen);
			if (maxLen > 0) {
				dp[i][j] = maxLen + 1;
			} else {
				dp[i][j] = 1;
			}
		}
		return dp[i][j];

	}
}
