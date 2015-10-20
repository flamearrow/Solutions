package paintHouse;

//There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
//The cost of painting each house with a certain color is different. 
//You have to paint all the houses such that no two adjacent houses have the same color.
//
//The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
//For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, 
//and so on... Find the minimum cost to paint all houses.
public class PaintHouse {
	public int minCost(int[][] costs) {
		if (costs.length == 0)
			return 0;
		int houses = costs.length;
		// dp[i][j] min cost of painting first i house, with last color j
		int[][] dp = new int[houses][3];
		for (int i = 0; i < 3; i++) {
			dp[0][i] = costs[0][i];
		}

		for (int i = 1; i < houses; i++) {
			for (int j = 0; j < 3; j++) {
				int[] colors = getColors(j);
				dp[i][j] = Math.min(dp[i - 1][colors[0]] + costs[i][j],
						dp[i - 1][colors[1]] + costs[i][j]);
			}
		}

		int ret = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (dp[houses - 1][i] < ret) {
				ret = dp[houses - 1][i];
			}
		}
		return ret;
	}

	int[] getColors(int j) {
		if (j == 0) {
			int[] ret = { 1, 2 };
			return ret;
		} else if (j == 1) {
			int[] ret = { 0, 2 };
			return ret;
		} else if (j == 2) {
			int[] ret = { 0, 1 };
			return ret;
		}
		return null;
	}
}
