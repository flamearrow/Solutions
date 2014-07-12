package graph.bipartite;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// check if a graph is bipartite or not
// a bipartite graph is a graph that can be divided into two groups A B
//  where ALL edges of the graph connect a node from A and another node from B
public class Solution {
	// the idea is if we have put a visited node and its neighbor in the same set 
	// then it's not a bipartite graph
	static boolean checkBipartite(List<Node> graph) {
		Set<Node> white = new HashSet<Node>();
		for (Node next : graph) {
			// next is white
			if (white.contains(next)) {
				for (Node n : next.neighbors)
					if (white.contains(n))
						return false;
			}
			// next is black
			else {
				for (Node n : next.neighbors)
					white.add(n);
			}
		}
		return true;
	}
}

class Node {
	int label;

	List<Node> neighbors;

	public Node(int label) {
		this.label = label;
		neighbors = new LinkedList<Node>();
	}
}