package cloneGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
	Map<UndirectedGraphNode, UndirectedGraphNode> backMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

	// BFS
	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
		if (node == null)
			return null;
		Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		q.offer(node);
		UndirectedGraphNode ret = new UndirectedGraphNode(node.label);
		backMap.put(node, ret);
		while (!q.isEmpty()) {
			// all enqueued nodes have already been cloned and put to map
			UndirectedGraphNode next = q.poll();
			UndirectedGraphNode nextClone = backMap.get(next);
			// probe next's neighbors
			for (UndirectedGraphNode child : next.neighbors) {
				if (!backMap.containsKey(child)) {
					UndirectedGraphNode nextCloneNeighbor = new UndirectedGraphNode(
							child.label);
					backMap.put(child, nextCloneNeighbor);
					q.offer(child);
				}
				nextClone.neighbors.add(backMap.get(child));
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		UndirectedGraphNode[] nodes = new UndirectedGraphNode[3];
		nodes[0] = new UndirectedGraphNode(0);
		nodes[1] = new UndirectedGraphNode(1);
		nodes[2] = new UndirectedGraphNode(2);
		nodes[0].neighbors.add(nodes[1]);
		nodes[0].neighbors.add(nodes[2]);
		nodes[1].neighbors.add(nodes[0]);
		nodes[1].neighbors.add(nodes[2]);
		nodes[2].neighbors.add(nodes[0]);
		nodes[2].neighbors.add(nodes[1]);

		Solution s = new Solution();
		UndirectedGraphNode nodeCopy = s.cloneGraph2(nodes[0]);
		System.out.println(nodeCopy);
	}

	// DFS
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		return node == null ? null : probNode(node);
	}

	UndirectedGraphNode probNode(UndirectedGraphNode node) {
		if (backMap.containsKey(node)) {
			return backMap.get(node);
		} else {
			UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
			backMap.put(node, newNode);
			for (UndirectedGraphNode nbr : node.neighbors) {
				newNode.neighbors.add(probNode(nbr));
			}
			return newNode;
		}
	}

}

class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}