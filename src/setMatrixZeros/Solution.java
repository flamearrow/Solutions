package setMatrixZeros;

//Given a m x n matrix, if an element is 0, 
// set its entire row and column to 0. Do it in place. 
public class Solution {
	public void setZeroes(int[][] matrix) {
		boolean clearFirstRow = false;
		int width = matrix[0].length;
		for (int i = 0; i < width; i++) {
			if (matrix[0][i] == 0)
				clearFirstRow = true;
		}

		for (int i = 1; i < matrix.length; i++) {
			boolean clearThisRow = false;
			for (int j = 0; j < width; j++) {
				if (matrix[i][j] == 0) {
					clearThisRow = true;
					matrix[0][j] = 0;
				}
			}
			if (clearThisRow) {
				for (int j = 0; j < width; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		for (int j = 0; j < width; j++) {
			// clear this column
			if (matrix[0][j] == 0) {
				for (int i = 0; i < matrix.length; i++) {
					matrix[i][j] = 0;
				}
			}
		}
		if (clearFirstRow) {
			for (int i = 0; i < width; i++) {
				matrix[0][i] = 0;
			}
		}
	}

	public static void main(String[] args) {
		int[][] m = { { 1, 0 }, { 0, 1 } };
		new Solution().setZeroes(m);
		System.out.println(m);
	}
}
