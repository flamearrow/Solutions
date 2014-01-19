package spiralMatrix;

import java.util.ArrayList;

//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
//For example,
//Given the following matrix:
//
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//
//You should return [1,2,3,6,9,8,7,4,5]. 
public class Solution {
	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (matrix.length == 0)
			return ret;
		int x = 0, y = 0;
		Direction dir = Direction.RIGHT;
		int width = matrix[0].length;
		int height = matrix.length;
		int loop = 0;
		while (true) {
			if (dir == Direction.RIGHT) {
				if (y < width - loop - 1) {
					ret.add(matrix[x][y]);
					y++;
				} else {
					ret.add(matrix[x][y]);
					if (x + 1 > height - loop - 1)
						break;
					dir = Direction.DOWN;
					x++;
				}
			} else if (dir == Direction.DOWN) {
				if (x < height - loop - 1) {
					ret.add(matrix[x][y]);
					x++;
				} else {
					ret.add(matrix[x][y]);
					if (y - 1 < loop)
						break;
					dir = Direction.LEFT;
					y--;
				}

			} else if (dir == Direction.LEFT) {
				if (y > loop) {
					ret.add(matrix[x][y]);
					y--;
				} else {
					ret.add(matrix[x][y]);
					if (x - 1 < loop + 1)
						break;
					dir = Direction.UP;
					x--;
				}
			} else if (dir == Direction.UP) {
				if (x > loop + 1) {
					ret.add(matrix[x][y]);
					x--;
				} else {
					ret.add(matrix[x][y]);
					loop++;
					if (y + 1 > width - loop - 1)
						break;
					dir = Direction.RIGHT;
					y++;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[][] m = { { 1 }, { 2 }, { 3 } };
		ArrayList<Integer> ret = new Solution().spiralOrder(m);
		for (int i : ret)
			System.out.println(i);

	}
}
