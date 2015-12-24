package shortestDistanceFromAllBuilding;

import java.util.LinkedList;
import java.util.Queue;

//You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
// You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
//Each 0 marks an empty land which you can pass by freely.
//Each 1 marks a building which you cannot pass through.
//Each 2 marks an obstacle which you cannot pass through.
//For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
//
//1 - 0 - 2 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
//
//Note:
//There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
public class Solution {

	public static void main(String[] args) {
		int[][] grid = { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0 } };
		int ret = new Solution().shortestDistance(grid);
		System.out.println(ret);
	}

	// the idea is to loop though all grid and start a BFS from each building
	// and mark each visitable cell how far is it away from the building
	// then create another buffer to buffer what is the accumulated minimal
	// distance from the current block to all buildings
	// d[i][j] minDistance from a building to i, j
	// total[i][j] min sum distance of all reachable building to i, j

	// the naive way is to find all 1s and flood the entire map, create d[i][j]
	// for each building and add that value to total[i][j]
	// note when BFS, the first time we hit i, j is the minDistance, so we only
	// mark d[i][j] the first time we hit it
	// but when we returns, we need another way to tell if total[i][j] is
	// reachable from all buildings
	// - an easy way would be count the time total[i][j] is hit and compare that
	// with number of buildings we see
	public int shortestDistance(int[][] grid) {
		int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int height = grid.length;
		int width = grid[0].length;
		int[][] total = new int[height][width];
		int[][] totalHit = new int[height][width];
		int buildings = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// found a building
				if (grid[i][j] == 1) {
					buildings++;
					int[][] d = new int[height][width];
					Queue<Pair> q = new LinkedList<>();
					q.add(new Pair(i, j));
					while (!q.isEmpty()) {
						Pair p = q.poll();
						for (int[] dir : dirs) {
							int newI = p.a + dir[0];
							int newJ = p.b + dir[1];
							if (newI >= 0 && newI < height && newJ >= 0
									&& newJ < width && grid[newI][newJ] == 0
									// only search for unvisited nodes
									&& d[newI][newJ] == 0
									// don't go back to origin
									&& !(newI == i && newJ == j)) {
								q.add(new Pair(newI, newJ));
								// this is BFS, newI, newJ is first visited,
								// it's bound to be shortest distance
								d[newI][newJ] = d[p.a][p.b] + 1;
								total[newI][newJ] += d[newI][newJ];
								totalHit[newI][newJ]++;
							}
						}
					}
				}
			}
		}

		// now total has all info, search for the candidate
		int ret = Integer.MAX_VALUE;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (totalHit[i][j] == buildings && total[i][j] < ret) {
					ret = total[i][j];
				}
			}
		}

		if (ret == Integer.MAX_VALUE) {
			return -1;
		} else {
			return ret;
		}

	}

	class Pair {
		int a, b;

		public Pair(int argA, int argB) {
			a = argA;
			b = argB;
		}
	}
}
