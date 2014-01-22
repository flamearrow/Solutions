package sudokuSolver;

//Write a program to solve a Sudoku puzzle by filling the empty cells.
//
//Empty cells are indicated by the character '.'.
//
//You may assume that there will be only one unique solution. 
public class Solution {
	// naive DFS
	public void solveSudoku(char[][] board) {
		Node start = board[0][0] == '.' ? new Node(0, 0) : getNextNode(
				new Node(0, 0), board);
		probe(board, start);
	}

	boolean probe(char[][] board, Node start) {
		// if start == null, then the entire board is in place
		if (start == null)
			return true;
		int x = start.x;
		int y = start.y;
		for (int i = 1; i <= 9; i++) {
			char newValue = (char) ('0' + i);
			board[x][y] = newValue;
			if (checkValid(board, start)) {
				start = getNextNode(start, board);
				if (probe(board, start))
					return true;
				// note here we need to reset start.x and start.y because start
				// will get updated in probe()
				else {
					start.x = x;
					start.y = y;
				}
			}
			// revert this back to '.'
			board[x][y] = '.';
		}
		return false;
	}

	boolean checkValid(char[][] board, Node n) {
		int x = n.x;
		int y = n.y;
		char value = board[x][y];
		for (int i = 0; i < 9; i++) {
			if (x != i && board[i][y] == value || y != i
					&& board[x][i] == value)
				return false;
		}
		int x0 = x == 9 ? 6 : x / 3 * 3;
		int y0 = y == 9 ? 6 : y / 3 * 3;
		for (int i = x0; i < x0 + 3; i++) {
			for (int j = y0; j < y0 + 3; j++) {
				if (i == x && j == y)
					continue;
				if (board[x][y] == '.')
					continue;
				if (board[x][y] == board[i][j])
					return false;
			}
		}
		return true;
	}

	// from left to right, up to down, excluding current
	Node getNextNode(Node current, char[][] board) {
		int x = current.x;
		int y = current.y + 1;
		for (int i = x; i < 9; i++) {
			if (i > x)
				y = 0;
			for (int j = y; j < 9; j++) {
				if (board[i][j] == '.')
					return new Node(i, j);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// int[][] b = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
		// { 6, 7, 2, 1, 9, 5, 3, 4, 8 }, { 1, 9, 8, 3, 4, 2, 5, 6, 7, },
		// { 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
		// { 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
		// { 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

		// int[][] b = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
		// { 6, 7, 2, 1, 9, 5, 3, 4, 8 }, { 1, 9, 8, 3, 4, 2, 5, 6, 7, },
		// { 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
		// { 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
		// { 2, 8, 7, 4, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		String[] bb = { "..9748...", "7........", ".2.1.9...", "..7...24.",
				".64.1.59.", ".98...3..", "...8.3.2.", "........6", "...2759.." };
		// char[][] board = getBoard(b);
		char[][] board = getBoard(bb);
		// char[][] board = new char[9][9];
		// for (int i = 0; i < 9; i++) {
		// for (int j = 0; j < 9; j++) {
		// board[i][j] = '.';
		// }
		// }
		new Solution().solveSudoku(board);
		System.out.println(board);

	}

	static char[][] getBoard(String[] board) {
		char[][] ret = new char[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				ret[i][j] = board[i].charAt(j);
			}
		}
		return ret;
	}

	static char[][] getBoard(int[][] board) {
		char[][] ret = new char[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != 0) {
					ret[i][j] = (char) ('0' + board[i][j]);
				} else {
					ret[i][j] = '.';
				}
			}
		}
		return ret;
	}
}

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
