package validSudoku;

import java.util.Arrays;

//Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
//
//The Sudoku board could be partially filled, where empty cells are filled
// with the character '.'.
public class Solution {

	// O(n^2)
	public boolean isValidSudoku2(char[][] board) {
		boolean[] visited = new boolean[10];

		for (int i = 0; i < 9; i++) {
			Arrays.fill(visited, false);
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.')
					if (visited[board[i][j] - '0']) {
						return false;
					} else {
						visited[board[i][j] - '0'] = true;
					}
			}
		}

		for (int i = 0; i < 9; i++) {
			Arrays.fill(visited, false);
			for (int j = 0; j < 9; j++) {
				if (board[j][i] != '.')
					if (visited[board[j][i] - '0']) {
						return false;
					} else {
						visited[board[j][i] - '0'] = true;
					}
			}
		}

		//check each 3 X 3 subsquare
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				Arrays.fill(visited, false);
				// upperLeft is board[i][j]
				for (int x = i; x < i + 3; x++) {
					for (int y = j; y < j + 3; y++) {
						if (board[x][y] != '.')
							if (visited[board[x][y] - '0']) {
								return false;
							} else {
								visited[board[x][y] - '0'] = true;
							}
					}
				}
			}
		}
		return true;
	}

	// O(n^3)
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
