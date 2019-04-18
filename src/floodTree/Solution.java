package floodTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Given a tree with arbitrary number of children, calculate the cost to flood this tree.
// The cost to flood a tree is defined as follows:
//   we need to flood a root of a tree first, this cost 1 time unit
//   we can only flood one direct child of a flooded root each time
//   the cost is the minimal time to flood the entire tree
// e.g the following tree's flooding cost is 3  
//           a
//	    / \
//         a   a
//	  /
//       a 
// e.g the following tree's flooding cost is 4  
//			a
//		    /   |   \
//		   a    a    a
//	          /
//	         a 
// e.g the following tree's cost is 5
//				a
//			   /  |   |  \   
//			  a   a   a   a
//			 /   /
//			a   a   
//                     /    
//		      a

public class Solution {
	public static void main(String[] args) {
		Node root = new Node();
		root.children.add(new Node());
		root.children.add(new Node());
		root.children.add(new Node());
		root.children.add(new Node());
		root.children.get(0).children.add(new Node());
		root.children.get(0).children.get(0).children.add(new Node());
		root.children.get(1).children.add(new Node());
		System.out.println(new Solution().cost(root));
	}

	// recursion, first calculate the cost of all child nodes
	// then sort them, we need to start flooding from the heaviest node.
	//  for each child node n, the cost would be (cost(n) + i) where i is the sequence of this child node
	// after we're done with the iteration, return the max cost, which would be the cost to flood the entire tree
	int cost(Node root) {
		if (root == null)
			return 0;
		List<Integer> childCosts = new ArrayList<Integer>();
		for (Node n : root.children) {
			childCosts.add(cost(n));
		}
		Collections.sort(childCosts);
		int tmpCost = 0;
		int cost = 0;
		int size = childCosts.size();
		for (int i = 0; i < childCosts.size(); i++) {
			tmpCost = i + childCosts.get(size - i - 1);
			if (tmpCost > cost) {
				cost = tmpCost;
			}
		}
		// +1 for root
		return cost + 1;
	}
}

class Node {
	List<Node> children;

	public Node() {
		children = new LinkedList<Node>();
	}
}
