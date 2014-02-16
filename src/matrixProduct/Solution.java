package matrixProduct;

// calculate the prodc of two matrix
public class Solution {
	// naive solution
	// if m1 is a*b and m2 is b*c, then result is a*c
	static int[][] product(int[][] m1, int[][] m2) {
		int height = m1.length;
		int width = m2[0].length;
		int len = m1[0].length;
		int[][] rst = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				for (int l = 0; l < len; l++) {
					rst[i][j] += m1[i][l] * m2[l][j];
				}
			}
		}
		return rst;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1 }, { 2 }, { 3 }, { 4 } };
		int[][] m2 = { { 5, 6, 7, 8 } };
		int[][] rst = product(m2, m1);
		for (int[] arr : rst) {
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println(" ");
		}
	}
}
