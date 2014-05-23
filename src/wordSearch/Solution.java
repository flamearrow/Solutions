package wordSearch;

//Given a 2D board and a word, find if the word exists in the grid.
//
//The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//For example,
//Given board =
//
//[
// ["ABCE"],
// ["SFCS"],
// ["ADEE"]
//]
//
//word = "ABCCED", -> returns true,
//word = "SEE", -> returns true,
//word = "ABCB", -> returns false.

// AKA boggle
public class Solution {
	// Note 1): When the current board[x][y] doesn't match wrod.charAt(index)
	// 				we need to treat it 2 ways: 
	// 				*) if it's in the middle of the word, we return 
	//  			*) if it's at the start of the word, we start with next char in the board
	// Note 2): When jumping to the next block, we increase y first, x second
	// 				need to terminate the loop when y and x both jump back to 0
	public boolean exist2(char[][] board, String word) {
		return doProbe(0, 0, board, new boolean[board.length][board[0].length],
				word, 0);
	}

	private boolean doProbe(int x, int y, char[][] board, boolean[][] visited,
			String word, int index) {
		if (index == word.length())
			return true;
		int width = board[0].length;
		int height = board.length;
		if (y >= width || y < 0 || x >= height || x < 0 || visited[x][y])
			return false;
		visited[x][y] = true;
		if (board[x][y] == word.charAt(index)
				&& (doProbe(x + 1, y, board, visited, word, index + 1)
						|| doProbe(x, y + 1, board, visited, word, index + 1)
						|| doProbe(x - 1, y, board, visited, word, index + 1) || doProbe(
							x, y - 1, board, visited, word, index + 1))) {
			return true;
		} else {
			visited[x][y] = false;
			// we only go to next block when we are at start
			// if we break in the middle of word, just keep going back
			if (index == 0) {
				y = (y + 1) % width;
				if (y == 0) {
					x = (x + 1) % height;
					// we're done the entire matrix
					if (x == 0)
						return false;
				}
				return doProbe(x, y, board, visited, word, 0);
			} else {
				return false;
			}
		}
	}
	
	
	// naive dfs search would take O(m*n*3^l) time
	// to avoid using maps: when we probed a node, set board[x][y] to a place holder '#' to mark as visited
	// then reset it when we failed in further probing
	public boolean exist(char[][] board, String word) {
		if (word.length() == 0)
			return false;
		char[] chars = word.toCharArray();
		boolean[][] maps = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (probe(board, chars, 0, i, j, maps)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean probe(char[][] board, char[] chars, int index, int i, int j,
			boolean[][] visited) {
		if (index == chars.length)
			return true;
		else {
			if (i < 0 || i == board.length || j < 0 || j == board[0].length
					|| board[i][j] != chars[index] || visited[i][j]) {
				return false;
			} else {
				visited[i][j] = true;
				// probe left
				if (probe(board, chars, index + 1, i, j - 1, visited)) {
					return true;
				}
				// probe right
				else if (probe(board, chars, index + 1, i, j + 1, visited)) {
					return true;
				}
				// probe up
				else if (probe(board, chars, index + 1, i - 1, j, visited)) {
					return true;
				}
				// probe down
				else if (probe(board, chars, index + 1, i + 1, j, visited)) {
					return true;
				}
				// clear this bit
				visited[i][j] = false;
				return false;
			}
		}
	}

	public static void main(String[] args) {
		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
				{ 'A', 'D', 'E', 'E' } };
		String word = "ABCCED";
		System.out.println(new Solution().exist(board, word));
	}
}
