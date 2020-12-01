package redundantConnection;

//In this problem, a tree is an undirected graph that is connected and has no cycles.
//
//        The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
//
//        The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
//
//        Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
public class Solution {
    public int[] findRedundantConnection(int[][] edges) {

        // use union find - create a array to remember the ancestor of each node.
        // If it's a valid tree, iterating through each edge, before adding the new node, it must
        // have a different ancestor of the graph's ancestor. If ever an new edge has two nodes
        // pointing to the same ancestor, then adding this edge would result in a cycle.

        int N = edges.length;
        int[] parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            // if before connecting the edge, we find the two nodes already have the same
            // ancestor, then adding the edge would make a loop
            if (findAncestor(parents, edge[0] - 1) == findAncestor(parents, edge[1] - 1)) {
                return edge;
            } else {
                union(parents, edge[0] - 1, edge[1] - 1);
            }
        }
        // can't reach here
        return null;

    }

    // initially each node is its own parent
    // child will be added gradually, all connected nodes will have the same ancestor
    int findAncestor(int[] parents, int toFind) {
        while (parents[toFind] != toFind) {
            toFind = parents[toFind];
        }
        return toFind;
    }

    boolean union(int[] parents, int node1, int node2) {
        int node1Ancestor = findAncestor(parents, node1);
        int node2Ancestor = findAncestor(parents, node2);
        // find a loop
        if (node1Ancestor == node2Ancestor) {
            return false;
        } else {
            parents[node1Ancestor] = node2Ancestor;
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] input = {{1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5}};
        for (int node : new Solution().findRedundantConnection(input)) {
            System.out.print(node + " ");
        }
    }


}
