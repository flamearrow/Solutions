package gameOfLife;

//According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
//
//Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
//
//Any live cell with fewer than two live neighbors dies, as if caused by under-population.
//Any live cell with two or three live neighbors lives on to the next generation.
//Any live cell with more than three live neighbors dies, as if by over-population..
//Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
//Write a function to compute the next state (after one update) of the board given its current state.
//
//Follow up: 
//Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
//In this question, we represent the board using a 2D array. 
// In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
public class Solution {
	static final int LIVE = 1;
	static final int DEAD = 0;
	static final int LIVE_MASK = 1 << 1;
	int[][] dirs = { { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public void gameOfLife(int[][] board) {
		int height = board.length;
		int width = board[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				update(board, i, j);
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				clearMask(board, i, j);
			}
		}
	}

	void clearMask(int[][] board, int x, int y) {
		if ((board[x][y] & LIVE_MASK) > 0) {
			board[x][y] = LIVE;
		} else {
			board[x][y] = DEAD;
		}
	}

	void update(int[][] board, int x, int y) {
		int height = board.length;
		int width = board[0].length;
		int livingCount = 0;
		for (int[] dir : dirs) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			if (newX >= 0 && newX < height && newY >= 0 && newY < width) {
				if ((board[newX][newY] & LIVE) > 0) {
					livingCount++;
				}
			}
		}
		// keep track of previous state
		if ((board[x][y] & LIVE) > 0) {
			board[x][y] |= LIVE_MASK;
		}

		// then update
		if ((board[x][y] & LIVE) > 0) {
			if (livingCount < 2 || livingCount > 3) {
				board[x][y] &= ~LIVE_MASK;
			} else {
				board[x][y] |= LIVE_MASK;
			}
		}
		if ((board[x][y] & LIVE) == DEAD && livingCount == 3) {
			board[x][y] |= LIVE_MASK;
		}
	}

}
