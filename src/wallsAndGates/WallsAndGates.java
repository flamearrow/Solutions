package wallsAndGates;

import java.util.LinkedList;

//You are given a m x n 2D grid initialized with these three possible values.
//
//-1 - A wall or an obstacle.
//0 - A gate.
//INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance 
// to a gate is less than 2147483647.
//Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//
//For example, given the 2D grid:
//INF  -1  0  INF
//INF INF INF  -1
//INF  -1 INF  -1
//  0  -1 INF INF
//After running your function, the 2D grid should be:
//  3  -1   0   1
//  2   2   1  -1
//  1  -1   2  -1
//  0  -1   3   4
public class WallsAndGates {
	public static void main(String[] args) {
		int[][] rooms = {
				{ Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1 },
				{ Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1 },
				{ 0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE } };
		new WallsAndGates().wallsAndGates(rooms);
		for (int[] array : rooms) {
			for (int i : array) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public void wallsAndGates(int[][] rooms) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if (rooms[i][j] == Integer.MAX_VALUE) {
					rooms[i][j] = bfsForShortestPath(rooms, i, j);
				}
			}
		}
	}

	int bfsForShortestPath(int[][] rooms, int i, int j) {
		LinkedList<Triple> q = new LinkedList<Triple>();
		q.add(new Triple(i, j, 0));
		boolean[][] visited = new boolean[rooms.length][rooms[0].length];
		int possibleMin = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Triple t = q.removeFirst();
			if (t.mI < 0 || t.mI >= rooms.length || t.mJ < 0
					|| t.mJ >= rooms[0].length) {
				continue;
			} else {
				if (visited[t.mI][t.mJ]) {
					continue;
				}
				if (rooms[t.mI][t.mJ] == 0) {
					return possibleMin < t.mStep ? possibleMin : t.mStep;
				} else if (rooms[t.mI][t.mJ] == -1) {
					continue;
				}
				// only probe max value
				else if (rooms[t.mI][t.mJ] == Integer.MAX_VALUE) {
					visited[t.mI][t.mJ] = true;
					q.add(new Triple(t.mI + 1, t.mJ, t.mStep + 1));
					q.add(new Triple(t.mI - 1, t.mJ, t.mStep + 1));
					q.add(new Triple(t.mI, t.mJ + 1, t.mStep + 1));
					q.add(new Triple(t.mI, t.mJ - 1, t.mStep + 1));
				}
				// if this cell has already got a number, keep possible min and
				// continue
				else {
					possibleMin = possibleMin < rooms[t.mI][t.mJ] + t.mStep ? possibleMin
							: rooms[t.mI][t.mJ] + t.mStep;
				}
			}
		}
		// might never touched any 0, directly return possibleMin
		return possibleMin;
	}

	class Triple {
		int mI, mJ;
		int mStep;

		public Triple(int i, int j, int step) {
			mI = i;
			mJ = j;
			mStep = step;
		}
	}
}
