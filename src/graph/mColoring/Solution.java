package graph.mColoring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// given a graph adj matrix and m colors, determine if the graph could be 
// covered by m colors such no two adjacent nodes have the same color
//
// naive approach: generating (V^m) different color configurations and check 
//   them one by one using BFS
// backtrack approach: DFS, first generating all valid colors of current node
//   continue probing by assigning one color - if we successfully colored all 
// 	 nodes then we're done, otherwise return false and try another color
public class Solution {
	public static void main(String[] args) {
		// triangle
		boolean[][] graphTriangle = { { false, true, true },
				{ true, false, true }, { true, true, false } };
		boolean[][] graphTako = { { false, true, true, true, false },
				{ true, false, true, false, true },
				{ true, true, false, true, true },
				{ true, false, true, false, true },
				{ false, true, true, true, false } };
		System.out.println("triangle with 2 colors:");
		for (int i : canCoverBackTrack(graphTriangle, 3)) {
			System.out.print(i + " ");
		}
		System.out.println("\ntako with 3 colors:");
		for (int i : canCoverBackTrack(graphTako, 3)) {
			System.out.print(i + " ");
		}
	}

	// return correct configuration for m colors or a int array filled with -1
	static int[] canCoverBackTrack(boolean[][] graph, int m) {
		int[] colors = new int[graph.length];
		Arrays.fill(colors, -1);
		probe(graph, colors, 0, m);
		// if it successes, colors would be set to correct configuration
		// if it fails, colors would be reset back to all -1
		// Arrays.fill(colors, -1);
		return colors;
	}

	static boolean probe(boolean[][] graph, int[] colors, int cur, int m) {
		if (cur == graph.length)
			return true;
		Set<Integer> validColors = new HashSet<Integer>();
		for (int i = 0; i < m; i++) {
			validColors.add(i);
		}
		for (int i = 0; i < graph.length; i++) {
			// for all colored neighbors, remove that 
			// color from current eligibles
			if (graph[cur][i] && colors[i] >= 0) {
				validColors.remove(colors[i]);
			}
		}
		for (int color : validColors) {
			colors[cur] = color;
			if (probe(graph, colors, cur + 1, m)) {
				return true;
			}
			colors[cur] = -1;
		}
		return false;
	}
}
