package longestIncreasingPath;

import java.util.Arrays;

//Given an integer matrix, find the length of the longest increasing path.
//
//        From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//
//        Example 1:
//
//        nums = [
//        [9,9,4],
//        [6,6,8],
//        [2,1,1]
//        ]
//        Return 4
//        The longest increasing path is [1, 2, 6, 9].
//
//        Example 2:
//
//        nums = [
//        [3,4,5],
//        [3,2,6],
//        [2,2,1]
//        ]
//        Return 4
//        The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
public class Solution {
    public static void main(String[] args) {
        int[][] nums = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(new Solution().longestIncreasingPath(nums));
    }

    public int longestIncreasingPath(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] dp = new int[height][width];
        int max = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int len = prob(matrix, i, j, dp);
                max = Math.max(len, max);
            }
        }
        return max;
    }

    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    int prob(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        dp[i][j] = 1;
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if (nextI < 0 || nextI >= matrix.length || nextJ < 0 || nextJ >= matrix[0].length) {
                continue;
            }
            if (matrix[i][j] < matrix[nextI][nextJ]) {
                dp[i][j] = Math.max(dp[i][j], 1 + prob(matrix, nextI, nextJ, dp));
            }
        }
        return dp[i][j];
    }
}
