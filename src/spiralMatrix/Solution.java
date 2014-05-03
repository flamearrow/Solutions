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
	// cleaner solution:
	// use four start/end pointers to lock bound
	// update bounds of next direction after we've done one direction, break if necessary
	// all bounds check conditions are the same
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int startX = 0, endX = matrix[0].length - 1;
		int startY = 0, endY = matrix.length - 1;
		while (true) {
			for (int i = startX; i <= endX; i++) {
				ret.add(matrix[startY][i]);
			}
			if (endY < ++startY)
				break;
			for (int i = startY; i <= endY; i++) {
				ret.add(matrix[i][endX]);
			}
			if (startX > --endX)
				break;
			for (int i = endX; i >= startX; i--) {
				ret.add(matrix[endY][i]);
			}
			if (startY > --endY)
				break;
			for (int i = endY; i >= startY; i--) {
				ret.add(matrix[i][startX]);
			}
			if (endX < ++startX)
				break;
		}
		return ret;
	}

	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	// use a loop to lock how far we can go, no need to use back array
	// this is shitty
	public ArrayList<Integer> spiralOrderShitty(int[][] matrix) {
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
		int[][] m = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 }, { 9, 10 } };
		ArrayList<Integer> ret = new Solution().spiralOrder(m);
		for (int i : ret)
			System.out.println(i);

	}
}
