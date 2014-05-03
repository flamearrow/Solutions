package searchA2DMatrix;

//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//    Integers in each row are sorted from left to right.
//    The first integer of each row is greater than the last integer of the previous row.
//
//For example,
//
//Consider the following matrix:
//
//[
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//
//Given target = 3, return true.
public class Solution {
	// no need for temp Node class, we can derive x and y coordinates on the fly
	public boolean searchMatrix2(int[][] matrix, int target) {
		int width = matrix[0].length;
		int start = 0;
		int end = matrix.length * width - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			int midX = mid / width;
			int midY = mid % width;
			if (matrix[midX][midY] == target)
				return true;
			if (matrix[midX][midY] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

	// just like a binary search, we need to come up with a way to calculate mid
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0)
			return false;
		int width = matrix[0].length;
		int start = new NNode(0, 0, width).toDecimal();
		int end = new NNode(matrix.length - 1, width - 1, width).toDecimal();

		while (start <= end) {
			int mid = (start + end) / 2;
			NNode midNode = new NNode(mid, width);
			if (matrix[midNode.i][midNode.j] == target) {
				return true;
			} else if (matrix[midNode.i][midNode.j] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = {};
		int target = 4;
		System.out.println(new Solution().searchMatrix(matrix, target));
	}
}

class NNode {
	int i, j, width;

	public NNode(int i, int j, int width) {
		this.i = i;
		this.j = j;
		this.width = width;
	}

	public NNode(int decimalValue, int width) {
		i = decimalValue / width;
		j = decimalValue % width;
	}

	int toDecimal() {
		return i * width + j;
	}
}
