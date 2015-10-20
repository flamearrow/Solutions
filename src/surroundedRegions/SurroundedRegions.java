package surroundedRegions;

import java.util.LinkedList;

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
public class SurroundedRegions {
	public static void main(String[] args) {
		char[][] board = { { 'X', 'X', 'X', 'X' }, { 'X', '0', '0', 'X' },
				{ 'X', 'X', '0', 'X' }, { 'X', '0', 'X', 'X' } };
		new SurroundedRegions().solve(board);
		for (char[] chars : board) {
			for (char c : chars) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}

	public void solve(char[][] board) {
		if (board.length == 0) {
			return;
		}
		int height = board.length;
		int width = board[0].length;
		for (int i = 0; i < height; i++) {
			if (board[i][0] == 'O') {
				mark(board, i, 0);
			}
			if (board[i][width - 1] == 'O') {
				mark(board, i, width - 1);
			}
		}
		for (int i = 0; i < width; i++) {
			if (board[0][i] == 'O') {
				mark(board, 0, i);
			}
			if (board[height - 1][i] == 'O') {
				mark(board, height - 1, i);
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == '1') {
					board[i][j] = 'O';
				}

			}
		}
	}

	class Node {
		int mI, mJ;

		Node(int i, int j) {
			mI = i;
			mJ = j;
		}
	}

	void mark(char[][] board, int i, int j) {
		int height = board.length;
		int width = board[0].length;
		boolean[][] visited = new boolean[height][width];
		LinkedList<Node> q = new LinkedList<>();
		q.add(new Node(i, j));
		while (!q.isEmpty()) {
			Node next = q.removeLast();
			if (next.mI < 0 || next.mI >= height || next.mJ < 0
					|| next.mJ >= width) {
				continue;
			} else if (visited[next.mI][next.mJ]) {
				continue;
			} else if (board[next.mI][next.mJ] == 'O') {
				visited[next.mI][next.mJ] = true;
				board[next.mI][next.mJ] = '1';
				q.add(new Node(next.mI + 1, next.mJ));
				q.add(new Node(next.mI - 1, next.mJ));
				q.add(new Node(next.mI, next.mJ + 1));
				q.add(new Node(next.mI, next.mJ - 1));
			}
		}
	}
}
