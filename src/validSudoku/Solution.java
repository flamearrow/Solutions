package validSudoku;

//Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
//
//The Sudoku board could be partially filled, where empty cells are filled
// with the character '.'.
public class Solution {

	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					if (!checkValid(board, i, j))
						return false;
				}
			}
		}
		return true;
	}

	boolean checkValid(char[][] board, int x, int y) {
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
}
