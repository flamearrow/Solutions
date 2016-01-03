package numIslands2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//A 2d grid map of m rows and n columns is initially filled with water. 
// We may perform an addLand operation which turns the water at position (row, col) into a land. 
// Given a list of positions to operate, count the number of islands after each addLand operation. 
// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
// You may assume all four edges of the grid are all surrounded by water.
//
//Example:
//
//Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
//Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
//
//0 0 0
//0 0 0
//0 0 0
//Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
//
//1 0 0
//0 0 0   Number of islands = 1
//0 0 0
//Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
//
//1 1 0
//0 0 0   Number of islands = 1
//0 0 0
//Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
//
//1 1 0
//0 0 1   Number of islands = 2
//0 0 0
//Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
//
//1 1 0
//0 0 1   Number of islands = 3
//0 1 0
//We return the result as an array: [1, 1, 2, 3]

// Union find:
// mark a node to its parent by roots[curNode] = parent
//  go all the way to top of a tree to find the uid of a tree
// to merge two trees, just do roots[curNode] = newRoot
public class Solution {
	public static void main(String[] args) {
		int[][] positions = { { 0, 0 }, { 0, 1 }, { 1, 2 }, { 2, 1 } };
		for (int i : new Solution().numIslands2Naive(3, 3, positions)) {
			System.out.println(i);
		}
	}

	// use union, represents islands by tree
	// roots[cur] is the root of cur island, a bunch of islands would belong to
	// one root
	// which is uid of these island
	// when merge two roots, just do root[rootToBeMerged] = newRoot
	// so that the subTree rooted from rootToBeMerged now become a leaf of new
	// root,
	// they eventually become one tree
	
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> ret = new LinkedList<Integer>();
		int[] roots = new int[m * n];
		int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		Arrays.fill(roots, -1);
		int isCount = 0;
		for (int[] pos : positions) {
			// map this to a one D problem
			int cur = pos[0] * n + pos[1];
			// set the new node as a stand alone island
			roots[cur] = cur;
			isCount++;
			for (int[] dir : dirs) {
				int x = pos[0] + dir[0];
				int y = pos[1] + dir[1];
				int newNode = x * n + y;
				if (x < 0 || x >= m || y < 0 || y >= n || roots[newNode] == -1) {
					continue;
				}
				int newRoot = getRoot(roots, newNode);

				if (newRoot != cur) {
					// merge - add cur as a leaf to the newRoot
					roots[cur] = newRoot;
					cur = newRoot;
					// if we merge, then there's no new isAdded
					isCount--;
				}
			}
			ret.add(isCount);
		}
		return ret;
	}

	// go to the root of the tree to find this tree's uid
	int getRoot(int[] roots, int newNode) {
		while (newNode != roots[newNode]) {
			newNode = roots[newNode];
		}
		return newNode;
	}
	
	
	// naively marking all islands to the same number
	public List<Integer> numIslands2Naive(int m, int n, int[][] positions) {
		int[][] map = new int[m][n];
		List<Integer> ret = new LinkedList<Integer>();
		ret.add(1);
		if (positions.length == 0) {
			return ret;
		}
		map[positions[0][0]][positions[0][1]] = 1;
		for (int i = 1; i < positions.length; i++) {
			ret.add(markMapAndReturnIsCount(map, positions[i], ret.get(i - 1),
					i + 1));
		}
		return ret;
	}

	int markMapAndReturnIsCount(int[][] map, int[] pair, int preCount, int isId) {
		// mark map, merge islands, if merged
		int ret = preCount + 1;
		int x = pair[0];
		int y = pair[1];
		map[x][y] = isId;
		if (isValid(map, x + 1, y) && map[x + 1][y] != 0
				&& map[x + 1][y] != isId) {
			ret--;
			mark(map, x + 1, y, isId);
		}
		if (isValid(map, x - 1, y) && map[x - 1][y] != 0
				&& map[x - 1][y] != isId) {
			ret--;
			mark(map, x - 1, y, isId);
		}
		if (isValid(map, x, y + 1) && map[x][y + 1] != 0
				&& map[x][y + 1] != isId) {
			ret--;
			mark(map, x, y + 1, isId);
		}
		if (isValid(map, x, y - 1) && map[x][y - 1] != 0
				&& map[x][y - 1] != isId) {
			ret--;
			mark(map, x, y - 1, isId);
		}

		return ret;
	}

	boolean isValid(int[][] map, int x, int y) {
		int width = map.length;
		int height = map[0].length;
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	void mark(int[][] map, int x, int y, int id) {
		if (!isValid(map, x, y) || map[x][y] == id || map[x][y] == 0) {
			return;
		}
		map[x][y] = id;
		mark(map, x + 1, y, id);
		mark(map, x - 1, y, id);
		mark(map, x, y + 1, id);
		mark(map, x, y - 1, id);
	}

}
