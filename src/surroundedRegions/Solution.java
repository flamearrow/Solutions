package surroundedRegions;

//Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
//
//A region is captured by flipping all 'O's into 'X's in that surrounded region .
//
//For example,
//
//X X X X
//X O O X
//X X O X
//X O X X
//
//After running your function, the board should be:
//
//X X X X
//X X X X
//X X X X
//X O X X

public class Solution {
	public void solve(char[][] board) {
		if (board.length == 0)
			return;
		int yLen = board.length;
		int xLen = board[0].length;
		boolean[][] touched = new boolean[yLen][xLen];
		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				if (board[y][x] == 'O') {
					probe(board, y, x, touched);
				}
			}
		}
	}

	// starting from the white node, probe and flip
	// return should flip or not
	boolean probe(char[][] board, int y, int x, boolean[][] touched) {
		// touch edge, can't be surrounded
		if (y < 0 || y >= board.length || x < 0 || x >= board[0].length)
			return false;
		// hit 'X', might be surround
		if (board[y][x] == 'X')
			return true;
		// prevent loop back
		if (touched[y][x]) {
			return true;
		}
		touched[y][x] = true;
		if (probe(board, y - 1, x, touched) && probe(board, y + 1, x, touched)
				&& probe(board, y, x - 1, touched)
				&& probe(board, y, x + 1, touched)) {
			board[y][x] = 'X';
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		char[][] board = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' },
				{ 'X', 'X', 'O', 'X' }, { 'X', 'O', 'X', 'X' } };
		new Solution().solve(board);
		printMatrix(board);
	}

	static void printMatrix(char[][] board) {
		for (char[] cc : board) {
			for (char c : cc) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
}
