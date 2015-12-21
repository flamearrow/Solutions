package paintHouse2;

import java.util.LinkedList;
import java.util.List;

//There are a row of n houses, each house can be painted with one of the k colors. 
// The cost of painting each house with a certain color is different. 
// You have to paint all the houses such that no two adjacent houses have the same color.
//
//The cost of painting each house with a certain color is represented by a n x k cost matrix. 
// For example, costs[0][0] is the cost of painting house 0 with color 0; 
// costs[1][2] is the cost of painting house 1 with color 2, and so on... 
// Find the minimum cost to paint all houses.
//
//Note:
//All costs are positive integers.
//
//Follow up:
//Could you solve it in O(nk) runtime?
public class Solution {
	public static void main(String[] args) {
		int[][] costs = { { 15, 17, 15, 20, 7, 16, 6, 10, 4, 20, 7, 3, 4 },
				{ 11, 3, 9, 13, 7, 12, 6, 7, 5, 1, 7, 18, 9 } };
		System.out.println(new Solution().minCostII2(costs));
	}

	public int minCostII2(int[][] costs) {
		// O(nk): to find the smallest cost of painting first n house with last
		// house color k
		// we don't need to compare all colors but k, we only keep track of
		// indices that ends
		// min1 and min2 of previous n-1 houses
		// when we consider dp[n][k], we only need to look if previous min1
		// indices i == k,
		// if true, then new min is min2 + cost[n][k]
		// otherwise new min is min1+cost[n][k]

		// modify costs to save some space
		if (costs.length == 0) {
			return 0;
		}
		int n = costs.length;
		int k = costs[0].length;
		int min1 = -1, min2 = -1;
		for (int i = 0; i < n; i++) {
			// last is the fixed value of previous houses
			// min is the two pointers used to find min1 and min2 of current house
			int last1 = min1;
			int last2 = min2;
			min1 = -1;
			min2 = -1;
			for (int j = 0; j < k; j++) {
				if (j != last1) {
					// < 0 is only for first house
					costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
				} else {
					costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
				}
				
				// here we're comparing same number of houses with different last color
				//  min1 and min2 will be the last1 and last2 of next house
				if (min1 < 0 || costs[i][j] < costs[i][min1]) {
					min2 = min1;
					min1 = j;
				} else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
					min2 = j;
				}
			}
		}
		// min1 points the min value
		return costs[n - 1][min1];
	}

	public int minCostII(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}
		int n = costs.length;
		int k = costs[0].length;
		// dp[i][j] the min cost of painting first i houses with last color j
		int[][] dp = new int[n][k];
		for (int i = 0; i < k; i++) {
			dp[0][i] = costs[0][i];
		}

		for (int house = 1; house < n; house++) {
			for (int newColor = 0; newColor < k; newColor++) {
				int minCost = Integer.MAX_VALUE;
				for (int availableColor : getColors(k, newColor)) {
					int cost = costs[house][newColor]
							+ dp[house - 1][availableColor];
					if (cost < minCost) {
						minCost = cost;
						dp[house][newColor] = cost;
					}
				}
			}
		}
		int ret = Integer.MAX_VALUE;
		for (int color = 0; color < k; color++) {
			if (dp[n - 1][color] < ret) {
				ret = dp[n - 1][color];
			}
		}
		return ret;
	}

	List<Integer> getColors(int allColors, int color) {
		List<Integer> ret = new LinkedList<>();
		for (int i = 0; i < allColors; i++) {
			if (i != color) {
				ret.add(i);
			}
		}
		return ret;
	}
}
