package cloneGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	Map<UndirectedGraphNode, UndirectedGraphNode> backMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

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