package numberOfIslands;

import java.util.LinkedList;

//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//Example 1:
//
//11110
//11010
//11000
//00000
//Answer: 1
//
//Example 2:
//
//11000
//11000
//00100
//00011
//Answer: 3
public class NumberOfIslands {
	public static void main(String[] args) {

	}

	public int numIslands(char[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		int ret = 0;
		int height = grid.length;
		int width = grid[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j] == '1') {
					flood2(grid, i, j);
					ret++;
				}
			}
		}
		return ret;
	}

	void flood2(char[][] grid, int i, int j) {
		int height = grid.length;
		int width = grid[0].length;
		if (i < 0 || i >= height || j < 0 || j >= width) {
			return;
		}

		if (grid[i][j] == '1') {
			grid[i][j] = 'x';
			flood2(grid, i + 1, j);
			flood2(grid, i - 1, j);
			flood2(grid, i, j + 1);
			flood2(grid, i, j - 1);
		} else {
			return;
		}
	}

	void flood(char[][] grid, int i, int j) {
		LinkedList<Node> q = new LinkedList<>();
		q.add(new Node(i, j));
		int height = grid.length;
		int width = grid[0].length;
		boolean[][] visited = new boolean[height][width];

		while (!q.isEmpty()) {
			Node next = q.removeLast();
			if (next.i < 0 || next.i >= height || next.j < 0 || next.j >= width
					|| visited[i][j]) {
				continue;
			} else if (grid[i][j] == '1') {
				visited[i][j] = true;
				grid[i][j] = 'X';
				q.add(new Node(next.i + 1, next.j));
				q.add(new Node(next.i - 1, next.j));
				q.add(new Node(next.i, next.j + 1));
				q.add(new Node(next.i, next.j - 1));
			}
		}
	}

	class Node {
		int i, j;

		public Node(int argI, int argJ) {
			i = argI;
			j = argJ;
		}
	}
}
