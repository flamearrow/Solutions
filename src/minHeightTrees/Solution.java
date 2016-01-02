package minHeightTrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
//
//Format
//The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
//
//You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//
//Example 1:
//
//Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//
//        0
//        |
//        1
//       / \
//      2   3
//return [1]
//
//Example 2:
//
//Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
//
//     0  1  2
//      \ | /
//        3
//        |
//        4
//        |
//        5
//return [3, 4]
public class Solution {
	// This is essentially look for the center of a graph
	// start from each leaf, move upwards, when two pointer meets, remove one
	// finally two pointers will meet, that is the root of min height tree
	// 1 - 2 - 3 - 4 - 5 - 6 : two roots, should be 3 or 4
	// 1 - 2 - 3 - 4 - 5 : one root, should be 3
	
	// the implemenation is essentially keep doing a topological sort until there're 
	//  1 or 2 nodes left
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) {
            List<Integer> ret = new ArrayList<Integer>();
            ret.add(0);
            return ret;
        }
		List<Set<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new HashSet<>());
		}
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (adj.get(i).size() == 1) {
				leaves.add(i);
			}
		}

		while (n > 2) {
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (int leaf : leaves) {
				// after removing leaf, its adjacent node would become a leaf
				int p = adj.get(leaf).iterator().next();
				adj.get(p).remove(leaf);
				if (adj.get(p).size() == 1) {
					newLeaves.add(p);
				}
			}
			leaves = newLeaves;
		}
		return leaves;
	}
}
