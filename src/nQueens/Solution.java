package nQueens;

import java.util.ArrayList;

//The n-queens puzzle is the problem of placing n queens on an n¡Án chessboard such that no two queens attack each other.
//
//
//
//Given an integer n, return all distinct solutions to the n-queens puzzle.
//
//Each solution contains a distinct board configuration of the n-queens' placement, 
//where 'Q' and '.' both indicate a queen and an empty space respectively.
public class Solution {
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
		ArrayList<String[]> ret = new Solution().solveNQueens(5);
		for (String[] ss : ret) {
			for (String s : ss) {
				System.out.println(s);
			}

			System.out.println();
		}
	}
}
