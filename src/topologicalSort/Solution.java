package topologicalSort;
// implement topological sort with DFS


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node {
    int val;
    List<Node> prerequisites;

    public Node(int val) {
        this.val = val;
        prerequisites = new ArrayList<>();
    }

    public void addPrepreq(Node n) {
        prerequisites.add(n);
    }

    @Override
    public String toString() {
        return "" + val;
    }
}

public class Solution {

    // DFS approach, at each loop search from arbitrary number, check if a node doesn't have
    // prereq or all its prereq is visited, if such node is found, add it to the next result.
    List<Node> sortDFS(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        while (!nodes.isEmpty()) {
            for (int i = 0; i < nodes.size(); i++) {
                Node nextNode = nodes.get(i);
                // if visit succeeds, it means this node has no prereq or all its prereq is visited
                if (visit(nextNode, visited)) {
                    result.add(nextNode);
                    nodes.remove(nextNode);
                }
            }
        }
        return result;
    }

    boolean visit(Node nodeToVisit, Set<Node> visited) {
        for (Node pre : nodeToVisit.prerequisites) {
            if (!visited.contains(pre)) {
                return false;
            }
        }
        visited.add(nodeToVisit);
        return true;
    }
    // Note: for the example, not necessarily 356 will be visited first: 3450621, 3562410 are both
    // valid answers.
    // to get a layer by layer result,
    // loop as long as there's unvisited nodes:
    // a) find all nodes that doesn't have incoming edge,
    // b) visited these nodes
    // c) remove all edges starting from the nodes just visited
    public static void main(String[] args) {
        List<Node> nodes = buildForest();
        new Solution().sortDFS(nodes).forEach(System.out::print);
    }

    //        1   0
    //      2   4
    //    6   5   3 - prereqs
    static List<Node> buildForest() {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(new Node(i));
        }
        list.get(1).addPrepreq(list.get(2));
        list.get(1).addPrepreq(list.get(4));
        list.get(0).addPrepreq(list.get(4));
        list.get(2).addPrepreq(list.get(6));
        list.get(2).addPrepreq(list.get(5));
        list.get(4).addPrepreq(list.get(5));
        list.get(4).addPrepreq(list.get(3));
        return list;
    }
}
