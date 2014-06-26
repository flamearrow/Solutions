package crackingTheCodingInterview.c18;

//Given an Matrix with integers, find the submatrix with max sum
public class Question12MaxSumsubSquare {
	// the O(n^3) idea is to two have two 'bars' scanning the matrix, each time calculate a temporary array
	// that represents the sums of columns between the two bars, and use the O(n) algorithm to calculate the largest
	// consecutive sum
	static void findMaxSum(int[][] matrix) {
		int[][] columnSum = calColumnSum(matrix);
		int width = matrix[0].length;
		int height = matrix.length;
		int[] columnHeights = new int[width];
		int maxSum = Integer.MIN_VALUE;
		int up = 0, bottom = 0, left = 0, right = 0;
		for (int i = 0; i < height; i++) {
			for (int j = i; j < height; j++) {
				// populate columnHeights with the subcolumns from matrix[i][x] to matrix[j][x]
				for (int k = 0; k < width; k++) {
					columnHeights[k] = i == 0 ? columnSum[j][k]
							: columnSum[j][k] - columnSum[i - 1][k];
				}
				// find the largest consecutive sum in columnHeights
				int curSum = 0, curLeft = 0;
				for (int k = 0; k < width; k++) {
					curSum += columnHeights[k];
					if (curSum > 0) {
						if (curSum > maxSum) {
							maxSum = curSum;
							left = curLeft;
							right = k;
							up = i;
							bottom = j;
						}
					} else {
						curSum = 0;
						curLeft = k + 1;
					}
				}
			}
		}

		System.out.println("max sum is " + maxSum + ", up:" + up + ", bottom:"
				+ bottom + ", left:" + left + ", right:" + right);
	}

	// ret[i][j] is the sum from ret[i][j] to upper bound (ret[i][j] to ret[0][j])
	static int[][] calColumnSum(int[][] matrix) {
		int width = matrix[0].length;
		int height = matrix.length;
		int[][] ret = new int[height][width];
		for (int i = 0; i < width; i++) {
			ret[0][i] = matrix[0][i];
		}

		for (int i = 0; i < width; i++) {
			for (int j = 1; j < height; j++) {
				ret[j][i] = ret[j - 1][i] + matrix[j][i];
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[][] matrix = { { -1, -2, -3 }, { -4, 5, 6 }, { -7, 8, 9 } };
		findMaxSum(matrix);
	}

}
