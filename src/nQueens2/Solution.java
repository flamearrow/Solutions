package nQueens2;

//Follow up for N-Queens problem.
//
//Now, instead outputting board configurations, return the total number of distinct solutions.
public class Solution {
	int ret = 0;

	public int totalNQueens(int n) {
		boolean[][] back = new boolean[n][n];
		probe(back, 0);
		return ret;
	}

	void probe(boolean[][] back, int row) {
		// done, add a new solution
		if (row == back.length) {
			ret++;
			return;
		}
		// find all valid positions in back[row]
		for (int i = 0; i < back.length; i++) {
			back[row][i] = true;
			if (valid(back, row, i)) {
				probe(back, row + 1);
			}
			back[row][i] = false;
		}
	}

	// check if back[row][column] breaks condition(only consider upper matrix
	// from [0-row]
	boolean valid(boolean[][] back, int row, int column) {
		// check column
		for (int i = 0; i < row; i++) {
			if (back[i][column])
				return false;
			// delta is row-i
			// check upper left diagnal
			if (column - row + i >= 0 && back[i][column - row + i])
				return false;

			// check upper right diagnal
			if (column + row - i < back.length && back[i][column + row - i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().totalNQueens(2));
	}
}
