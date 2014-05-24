package rotateImage;

//You are given an n x n 2D matrix representing an image.
//
//Rotate the image by 90 degrees (clockwise).
//
//Follow up:
//Could you do this in-place?
public class Solution {
	public void rotate2(int[][] matrix) {
		int height = matrix.length;
		int width = height;
		int start = 0, end = width - 1;
		while (start < end) {
			for (int i = 0; i < height; i++) {
				int tmp = matrix[start][i];
				matrix[start][i] = matrix[end][i];
				matrix[end][i] = tmp;
			}
			start++;
			end--;
		}
		for (int i = 0; i < width; i++) {
			for (int j = i + 1; j < width; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
	}

	public void rotate(int[][] matrix) {
		// diagnal and symmetric
		int n = matrix.length;
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}

		int s = 0, e = n - 1;
		while (s < e) {
			for (int i = 0; i < n; i++) {
				tmp = matrix[i][s];
				matrix[i][s] = matrix[i][e];
				matrix[i][e] = tmp;
			}
			s++;
			e--;
		}
	}

	public static void main(String[] args) {
		int[][] m = { { 1, 2 }, { 3, 4 } };
		new Solution().rotate2(m);
		System.out.println(m);
	}
}
