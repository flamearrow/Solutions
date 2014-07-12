package graph.fordFulkerson;

import java.util.LinkedList;
import java.util.Queue;

// Ford Fulkerson algorithm is to compute the max flow from S to T in a graph 
// the idea is to use BFS/DFS to find available paths from residual graph
// and add the sum to newly found path
// To find augmenting path, there're two ways:
//   increasing path: edge is positive direction - remove from residual
//   decreasing path: edge is negative direction - add to residual
// invariant is the flow going into a node should be equal to the flow going out from a node
public class Solution {
	public static void main(String[] args) {
		int[][] graph = { { 0, 10, 0, 10, 0, 0 }, { 0, 0, 9, 0, 0, 0 },
				{ 0, 0, 0, 0, 6, 10 }, { 0, 2, 8, 0, 4, 0 },
				{ 0, 0, 0, 0, 0, 10 }, { 0, 0, 0, 0, 0, 0 } };
		enrich(graph);
		System.out.println(maxFlow(graph, 0, 5));
	}

	// graph[i][j] = -graph[j][i]
	static void enrich(int[][] graph) {
		int size = graph.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (graph[i][j] > 0) {
					graph[j][i] = -graph[i][j];
				}
			}
		}
	}

	static int curFlow = 0;

	// input is a symmetric matrix
	static int maxFlow(int[][] graph, int start, int target) {
		int[][] residual = graph.clone();
		int ret = 0;
		// keep augmenting until there's no more augmenting path
		while (bfs(residual, start, target)) {
			ret += curFlow;
			curFlow = 0;
		}
		return ret;
	}

	// find if there's an augmenting path between start and target
	// there's an augmenting path edge between u and v if
	//   residual[u][v] > 0 || residual[v][u] < 0
	// the method will also update residual along the path
	static boolean bfs(int[][] residual, int start, int target) {
		int size = residual.length;
		boolean[] visited = new boolean[size];
		visited[start] = true;
		int[] parent = new int[size];
		parent[start] = -1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < size; i++) {
				if (!visited[i]
						&& (residual[cur][i] > 0 || residual[i][cur] < 0)) {
					parent[i] = cur;
					visited[i] = true;
					q.offer(i);
				}
			}
		}
		if (!visited[target])
			return false;
		else {
			// find a path, use parent[] to find the path, update residual[][] and curFlow
			int minFlow = Integer.MAX_VALUE;
			int cur = target;
			while (parent[cur] != -1) {
				int curEdge = residual[parent[cur]][cur] > 0 ? residual[parent[cur]][cur]
						: -residual[parent[cur]][cur];
				minFlow = Math.min(curEdge, minFlow);
				cur = parent[cur];
			}
			// loop through the path again to update residual
			cur = target;
			// note: when we update edge, 
			// we need to make sure that both forward and back ward edges are updated
			while (parent[cur] != -1) {
				// forward edge, minus
				if (residual[parent[cur]][cur] > 0) {
					residual[parent[cur]][cur] -= minFlow;
					residual[cur][parent[cur]] += minFlow;
				}
				// backward edge, plus
				else {
					residual[parent[cur]][cur] += minFlow;
					residual[cur][parent[cur]] -= minFlow;
				}
				cur = parent[cur];
			}
			curFlow = minFlow;
			return true;
		}
	}
}
