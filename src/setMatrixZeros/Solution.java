package setMatrixZeros;

//Given a m x n matrix, if an element is 0, 
// set its entire row and column to 0. Do it in place. 
public class Solution {
	public void setZeroes2(int[][] matrix) {
		boolean clearFirstRow = false, clearFirstColumn = false;
		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0)
				clearFirstRow = true;
		}
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0)
				clearFirstColumn = true;
		}

		// when setting bit, can't touch the first line/column
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}

		for (int i = 1; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				for (int j = 1; j < matrix.length; j++) {
					matrix[j][i] = 0;
				}
			}
		}

		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < matrix[0].length; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		if (clearFirstRow) {
			for (int i = 0; i < matrix[0].length; i++) {
				matrix[0][i] = 0;
			}
		}

		if (clearFirstColumn) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}

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
		int[][] m = { { 0, 0, 0, 5 }, { 4, 3, 1, 4 }, { 0, 1, 1, 4 },
				{ 1, 2, 1, 3 }, { 0, 0, 1, 1 } };
		new Solution().setZeroes2(m);
		System.out.println(m);
	}
}
