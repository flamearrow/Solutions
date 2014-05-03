package nQueens2;

//Follow up for N-Queens problem.
//
//Now, instead outputting board configurations, return the total number of distinct solutions.
public class Solution {

	int result;

	public int totalNQueensFancy2(int n) {
		int fullLine = (1 << n) - 1;
		probeNQueens(0, 0, 0, fullLine);
		return result;
	}

	public void probeNQueens(int lineMask, int leftMask, int rightMask,
			int fullLine) {
		if (lineMask == fullLine) {
			result++;
			return;
		}
		int available = (~(lineMask | leftMask | rightMask)) & fullLine;
		while (available > 0) {
			// right most set bit
			int currentUse = available & (-available);
			// remove this from available
			available ^= currentUse;
			// for diagnose conflicts, 
			// we need to consider 
			// 1) the left/right bit of all previous left/right conflicts
			// 2) the left/right bit of current set bit
			probeNQueens(lineMask | currentUse, (currentUse | leftMask) << 1,
					(currentUse | rightMask) >> 1, fullLine);
		}
	}

	// this is a fancy bit manipulation solution
	// it uses a bit vector to represent a line
	// 10101 means the second and forth one is available

	// to calculate the last 1 of a binary, do b & (-b)
	// i.e f(1010)=1010&(0110)=0010
	int fullLine = 0;
	int fancyRet = 0;

	public int totalNQueensFancy(int n) {
		fullLine = (1 << n) - 1;
		probeFancy(0, 0, 0);
		return fancyRet;
	}

	// row is a bit vector that represents available slots
	//  dictated by column
	// lD and rD are also bit vector that represents available slots
	//  dictated by left diagonal and right diagonal
	void probeFancy(int row, int lD, int rD) {
		int available, occupy;
		if (row != fullLine) {
			// occupied is 1, satisfy constraints from row, lD and rD
			// now available[i]==1 means ith bit is available

			// need to clear higher bits because we are shifting lD and rD
			available = fullLine & (~(row | lD | rD));
			while (available != 0) {
				// find the right most one of available
				// and occupy it, continue probing
				occupy = available & (-available);
				available -= occupy;
				// which bits are not available in next row? 
				// 1) the occupied bit in this run plus all previous occupied bit 
				// 2) all previous occupied left diagonal bit plus current occupied bit LEFT SHIFT 1
				// 		i.e: previous lD = 0101, occupied = 0010, then next lD would be 1110;
				// 3) all previous occupied right diagonal bit plus current occupied bit RIGHT SHIFT 1
				probeFancy(row | occupy, (lD | occupy) << 1, (rD | occupy) >> 1);
			}
		}
		// at some point we have filled the entire row, then we have find a solution
		else {
			fancyRet++;
		}
	}

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
		System.out.println(new Solution().totalNQueensFancy2(2));
	}
}
