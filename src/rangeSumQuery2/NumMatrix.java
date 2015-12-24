package rangeSumQuery2;

//Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by 
// its upper left corner (row1, col1) and lower right corner (row2, col2).
//
//Range Sum Query 2D
//The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), 
// which contains sum = 8.
//
//Example:
//Given matrix = [
//  [3, 0, 1, 4, 2],
//  [5, 6, 3, 2, 1],
//  [1, 2, 0, 1, 5],
//  [4, 1, 0, 1, 7],
//  [1, 0, 3, 0, 5]
//]
//
//sumRegion(2, 1, 4, 3) -> 8
//sumRegion(1, 1, 2, 2) -> 11
//sumRegion(1, 2, 2, 4) -> 12
//Note:
//You may assume that the matrix does not change.
//There are many calls to sumRegion function.
//You may assume that row1 ≤ row2 and col1 ≤ col2.
public class NumMatrix {

	public static void main(String[] args) {
		int[][] matrix = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 },
				{ 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } };
		NumMatrix n = new NumMatrix(matrix);
	}

	int[][] hor;
	int width, height;

	public NumMatrix(int[][] matrix) {
		if (matrix.length == 0) {
			height = 0;
			width = 0;
			return;
		}
		height = matrix.length;
		width = matrix[0].length;
		hor = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = width - 1; j >= 0; j--) {
				hor[i][j] = ((j == width - 1) ? 0 : hor[i][j + 1]) + matrix[i][j];
			}
		}
		System.out.println("ml");
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (width == 0) {
			return 0;
		}
		int ret = 0;
		for (int i = row1; i <= row2; i++) {
			ret += hor[i][col1] - ((col2 == width - 1) ? 0 : hor[i][col2 + 1]);
		}
		return ret;
	}
}
