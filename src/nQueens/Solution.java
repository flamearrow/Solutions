package nQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//The n-queens puzzle is the problem of placing n queens on an n��n chessboard such that no two queens attack each other.
//
//
//
//Given an integer n, return all distinct solutions to the n-queens puzzle.
//
//Each solution contains a distinct board configuration of the n-queens' placement, 
//where 'Q' and '.' both indicate a queen and an empty space respectively.
public class Solution {

	// note: to check if a half populated matrix is valid
	// we just need to check from the current line and above
	public List<String[]> solveNQueens2(int n) {
		char[][] cur = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(cur[i], '.');
		}
		List<String[]> ret = new LinkedList<String[]>();
		probe2(cur, 0, ret);
		return ret;
	}

	void probe2(char[][] cur, int curRow, List<String[]> ret) {
		if (curRow == cur.length) {
			String[] newMatrix = new String[cur.length];
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < cur.length; i++) {
				sb.setLength(0);
				for (int j = 0; j < cur.length; j++) {
					sb.append(cur[i][j]);
				}
				newMatrix[i] = sb.toString();
			}
			ret.add(newMatrix);
		} else {
			for (int i = 0; i < cur.length; i++) {
				cur[curRow][i] = 'Q';
				if (valid(cur, curRow, i)) {
					probe2(cur, curRow + 1, ret);
				}
				cur[curRow][i] = '.';
			}
		}
	}

	// check if cur[i][j] breaks the rule
	// note for diagnal we only need to check upperLeft and upperRight
	boolean valid(char[][] cur, int i, int j) {
		int n = cur.length;
		for (int c = 0; c < n; c++) {
			// column
			if (c != i && cur[c][j] == 'Q')
				return false;
			// upperLeft
			if ((i - c - 1 >= 0) && (j - c - 1 >= 0)
					&& cur[i - c - 1][j - c - 1] == 'Q')
				return false;
			// upperRight
			if ((i - c - 1 >= 0) && (j + c + 1 < n)
					&& cur[i - c - 1][j + c + 1] == 'Q')
				return false;
		}
		return true;
	}

	// rec
	public ArrayList<String[]> solveNQueens(int n) {
		boolean[][] back = new boolean[n][n];
		ArrayList<String[]> ret = new ArrayList<String[]>();
		probe(ret, back, 0);
		return ret;
	}

	void probe(ArrayList<String[]> ret, boolean[][] back, int row) {
		// done, add a new solution
		if (row == back.length) {
			StringBuilder sb = new StringBuilder();
			String[] newSolution = new String[back.length];
			int i = 0;
			for (boolean[] sBool : back) {
				for (boolean b : sBool) {
					if (b)
						sb.append('Q');
					else
						sb.append('.');
				}
				newSolution[i++] = sb.toString();
				sb.setLength(0);
			}
			ret.add(newSolution);
			return;
		}
		// find all valid positions in back[row]
		for (int i = 0; i < back.length; i++) {
			back[row][i] = true;
			if (valid(back, row, i)) {
				probe(ret, back, row + 1);
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
		List<String[]> ret = new Solution().solveNQueens2(4);
		for (String[] ss : ret) {
			for (String s : ss) {
				System.out.println(s);
			}

			System.out.println();
		}
	}
}
