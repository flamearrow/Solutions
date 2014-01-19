package spiralMatrix2;

//Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
//
//For example,
//Given n = 3,
//You should return the following matrix:
//
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]

public class Solution {
	enum Direction {
		LEFT, RIGHT, UP, DOWN
	}
	// the newly created array itself is a marker
	public int[][] generateMatrix(int n) {
		int[][] ret = new int[n][n];
		int x = 0, y = 0, cnt = 1;
		Direction dir = Direction.RIGHT;
		while (x >= 0 && x < n && y >= 0 && y < n && ret[x][y] == 0) {
			ret[x][y] = cnt++;
			if (dir == Direction.RIGHT) {
				if (y < n - 1 && ret[x][y + 1] == 0) {
					y++;
				} else {
					dir = Direction.DOWN;
					x++;
				}
			} else if (dir == Direction.DOWN) {
				if (x < n - 1 && ret[x + 1][y] == 0)
					x++;
				else {
					dir = Direction.LEFT;
					y--;
				}
			} else if (dir == Direction.LEFT) {
				if (y > 0 && ret[x][y - 1] == 0)
					y--;
				else {
					dir = Direction.UP;
					x--;
				}
			} else if (dir == Direction.UP) {
				if (x > 0 && ret[x - 1][y] == 0)
					x--;
				else {
					dir = Direction.RIGHT;
					y++;
				}

			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[][] ret = new Solution().generateMatrix(4);
		System.out.println(ret);
	}
}
