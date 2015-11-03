package bestMeetingPoint;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//A group of two or more people wants to meet and minimize the total travel distance. 
// You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
// The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
//
//For example, given three people living at (0,0), (0,4), and (2,2):
//
//1 - 0 - 0 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
public class Solution {
	// idea is the mid point of a couple of points is the shortest distance, calculate it in two dimensions
	public int minTotalDistance(int[][] grid) {
		int height = grid.length;
		int width = grid[0].length;
		List<Integer> col = new LinkedList<>();
		List<Integer> row = new LinkedList<>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j] == 1) {
					col.add(i);
					row.add(j);
				}
			}
		}
		Collections.sort(row);
		int midIndex = col.size() / 2;
		int ret = 0;
		for (int i : col) {
			ret += Math.abs(i - col.get(midIndex));
		}
		for (int i : row) {
			ret += Math.abs(i - row.get(midIndex));
		}
		return ret;
	}
}
