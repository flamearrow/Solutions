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
