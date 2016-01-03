package numberOfConnectedComponentsInAUndirectedGraph;

import java.util.*;

//Given n nodes labeled from 0 to n - 1 and a list of undirected edges
// (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
//
//        Example 1:
//        0          3
//        |          |
//        1 --- 2    4
//        Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
//
//        Example 2:
//        0           4
//        |           |
//        1 --- 2 --- 3
//        Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
public class Solution {
    public static void main(String[] args) {
        int[][] edges = {{5, 6}, {0, 2}, {1, 7}, {5, 9}, {1, 8}, {3, 4}, {0, 6}, {0, 7}, {0, 3}, {8, 9}};
        System.out.println(new Solution().countComponents2(10, edges));
    }

    public int countComponents(int n, int[][] edges) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] edge : edges) {
            nodes[edge[0]].addNeighbour(nodes[edge[1]]);
            nodes[edge[1]].addNeighbour(nodes[edge[0]]);
        }
        Set<Node> set = new HashSet<>();
        for (Node nn : nodes) {
            set.add(nn);
        }
        int ret = 0;
        while (!set.isEmpty()) {
            ret++;
            removeShit(set);
        }

        return ret;
    }

    void removeShit(Set<Node> set) {
        Node n = set.iterator().next();
        Queue<Node> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            Node next = q.poll();
            set.remove(next);
            for (Node neighbour : next.neighbours) {
                if (set.contains(neighbour)) {
                    q.add(neighbour);
                }
            }
        }
    }

    class Node {
        int val;
        List<Node> neighbours;

        public Node(int vVal) {
            val = vVal;
            neighbours = new LinkedList<>();
        }

        void addNeighbour(Node n) {
            neighbours.add(n);
        }
    }

    // use union find
    // The idea of union find is to loop through each node and find which root the node belongs to
    // each time a new node is hit, first create itself as a island, i.e mark it self as its root
    //  then look at each of its neighbors root,
    //  if its neighbour has a root, then the new node doesn't form a standalone island, just merge
    //   the current node with the existing island as a new island isNew
    //  now keep looking its other neighbors, if they belong to a different island, merge isNew with
    //  that new island - this is essentially happening when the current node we're looking at connects
    //  two separated islands.
    //  Each time we merge, the total isCounts decrease by one

    public int countComponents2(int n, int[][] edges) {
        int[] roots = new int[n];
        Node[] nodes = new Node[n];
        Arrays.fill(roots, -1);
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] edge : edges) {
            nodes[edge[0]].addNeighbour(nodes[edge[1]]);
            nodes[edge[1]].addNeighbour(nodes[edge[0]]);
        }
        int ret = 0;
        for (Node node : nodes) {
            // add a new island
            ret++;
            roots[node.val] = node.val;
            int cur = node.val;
            for (Node ne : node.neighbours) {
                if (roots[ne.val] != -1) {
                    int newRoot = getRoot(ne.val, roots);
                    // turns out the node we just visited is adjacent to another island, merge the two
                    //  and we're not really adding a new island here
                    // chances are this neight just connected two island, in which case we also need to ret--
                    // because we just removed an island
                    if (newRoot != roots[cur]) {
                        roots[cur] = newRoot;
                        cur = newRoot;
                        ret--;
                    }
                }
            }
        }
        return ret;
    }

    int getRoot(int val, int[] roots) {
        while (val != roots[val]) {
            val = roots[val];
        }
        return val;
    }
}
