package numberOfIslands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
// write a function to check whether these edges make up a valid tree.
//
//For example:
//
//Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
//
//Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
public class GraphValidTree {
	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 2, 4 } };
		System.out.println(new GraphValidTree().validTree(5, edges));
	}

	// the idea is to check each edge and see if by marking two nodes of an edge
	// visited for each edge, can we loop back to an edge
	// that's already visited, if so there's a loop, otherwise we'll exhaust all
	// nodes without seeing an node that's already visited
	public boolean validTree(int n, int[][] edges) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(i, new HashSet<Integer>());
		}
		for (int[] pair : edges) {
			map.get(pair[0]).add(pair[1]);
			map.get(pair[1]).add(pair[0]);
		}

		LinkedList<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		q.add(0);
		while (!q.isEmpty()) {
			int next = q.removeFirst();
			// loop
			if (visited[next]) {
				return false;
			}
			visited[next] = true;
			// all nodes connected to next
			q.addAll(map.get(next));
			// remove the edge
			for (int start : map.get(next)) {
				map.get(start).remove(next);
			}
		}

		// check if it's a forest
		for (boolean b : visited) {
			if (!b) {
				return false;
			}
		}

		return true;
	}
}
