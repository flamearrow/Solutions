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
	public static int LEFT = 1 << 0;
	public static int RIGHT = 1 << 1;
	public static int UP = 1 << 2;
	public static int DOWN = 1 << 3;

	// naive dfs search would take O(m*n*l) time
	public boolean exist(char[][] board, String word) {
		if (word.length() == 0)
			return false;
		char[] chars = word.toCharArray();
		boolean[][] maps = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				clearMap(maps);
				if (probe(board, chars, 0, i, j, maps)) {
					return true;
				}
			}
		}
		return false;
	}

	void clearMap(boolean[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = false;
			}
		}
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
				return false;
			}
		}
	}

	public static void main(String[] args) {
		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
				{ 'A', 'D', 'E', 'E' } };
		String word = "ABCB";
		System.out.println(new Solution().exist(board, word));
	}
}
