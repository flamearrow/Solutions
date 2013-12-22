package triangle;

import java.util.ArrayList;

//Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
//
//For example, given the following triangle
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]

//The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//
//Note:
//Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

public class Solution {

	// since we're just checking the left and right child, after looking at it
	// once, we won't visit the same node again,
	// i.e :
	// (2,1) will only be visited as the right child of (1,0) and left child of
	// (1,1)
	int[][] minPath;

	int[] minPathON;

	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		minPath = new int[triangle.size()][triangle.get(triangle.size() - 1)
				.size()];
		for (int i = 0; i < minPath.length; i++) {
			for (int j = 0; j < minPath[0].length; j++) {
				minPath[i][j] = Integer.MAX_VALUE;
			}
		}
		minPathON = new int[triangle.size()];
		for (int i = 0; i < minPathON.length; i++) {
			minPathON[i] = Integer.MAX_VALUE;
		}
		// return probeMin(triangle, 0, 0, 0);
		return probeMinONSpace(triangle, 0, 0, 0);
	}

	// probeMinONSpace or probeMin is looking for the min path from current node
	// to bottom plus currentSum
	// it's always returning the sum of entire root-leaf path

	int probeMinONSpace(ArrayList<ArrayList<Integer>> triangle, int row,
			int column, int currentSum) {
		if (row == triangle.size()) {
			return currentSum;
		}
		int parentPathSum = currentSum;
		currentSum += triangle.get(row).get(column);
		// if left child is not left most node of a row, then it must be visited
		// before
		int left = Integer.MAX_VALUE;
		if (column != 0 && row + 1 < triangle.size()) {
			left = minPathON[row + 1] + currentSum;
		} else {
			left = probeMinONSpace(triangle, row + 1, column, currentSum);
		}
		int right = probeMinONSpace(triangle, row + 1, column + 1, currentSum);
		int ret = left < right ? left : right;
		minPathON[row] = ret - parentPathSum;
		return ret;
	}

	int probeMin(ArrayList<ArrayList<Integer>> triangle, int row, int column,
			int currentSum) {
		if (row == triangle.size()) {
			return currentSum;
		}
		if (minPath[row][column] < Integer.MAX_VALUE)
			return currentSum + minPath[row][column];
		int parentPathSum = currentSum;
		currentSum += triangle.get(row).get(column);
		int left = probeMin(triangle, row + 1, column, currentSum);
		int right = probeMin(triangle, row + 1, column + 1, currentSum);
		int ret = left < right ? left : right;
		if (minPath[row][column] > ret - parentPathSum) {
			minPath[row][column] = ret - parentPathSum;
		}
		return ret;
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();
		triangle.add(new ArrayList<Integer>());
		triangle.add(new ArrayList<Integer>());
		triangle.add(new ArrayList<Integer>());
		triangle.add(new ArrayList<Integer>());
		triangle.get(0).add(2);
		triangle.get(1).add(3);
		triangle.get(1).add(4);
		triangle.get(2).add(6);
		triangle.get(2).add(5);
		triangle.get(2).add(7);
		triangle.get(3).add(4);
		triangle.get(3).add(1);
		triangle.get(3).add(8);
		triangle.get(3).add(3);
		System.out.println(new Solution().minimumTotal(triangle));

	}
}
