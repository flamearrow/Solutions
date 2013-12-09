package copyListWithRandomPointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//
//Return a deep copy of the list. 
public class Solution {
	public RandomListNode copyRandomList(RandomListNode head) {
		// use a map to map node pointed to in the ori list and nodes pointing from in the copied list
		if (head == null)
			return null;
		RandomListNode ret = new RandomListNode(head.label);
		RandomListNode retPtr = ret;
		RandomListNode oriPtr = head;
		Map<RandomListNode, Set<RandomListNode>> backMap = new HashMap<RandomListNode, Set<RandomListNode>>();
		if (head.random != null) {
			Set<RandomListNode> set = new HashSet<RandomListNode>();
			set.add(ret);
			backMap.put(head.random, set);
		}
		// link sequence nodes
		while (oriPtr.next != null) {
			retPtr.next = new RandomListNode(oriPtr.next.label);
			RandomListNode randNode = oriPtr.next.random;
			if (randNode != null) {
				if (backMap.containsKey(randNode)) {
					backMap.get(randNode).add(retPtr.next);
				} else {
					Set<RandomListNode> set = new HashSet<RandomListNode>();
					set.add(retPtr.next);
					backMap.put(randNode, set);
				}
			}
			retPtr = retPtr.next;
			oriPtr = oriPtr.next;
		}

		// link random nodes
		oriPtr = head;
		retPtr = ret;
		while (oriPtr != null) {
			if (backMap.containsKey(oriPtr))
				for (RandomListNode node : backMap.get(oriPtr)) {
					node.random = retPtr;
				}
			oriPtr = oriPtr.next;
			retPtr = retPtr.next;
		}

		return ret;
	}

	public static void main(String[] args) {
		RandomListNode n0 = new RandomListNode(0);
		RandomListNode n1 = new RandomListNode(1);
		RandomListNode n2 = new RandomListNode(2);
		RandomListNode n3 = new RandomListNode(3);
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n0.random = n2;
		n1.random = n2;
		n2.random = null;
		n3.random = n0;

		RandomListNode nc = new Solution().copyRandomList(n0);
		System.out.println(nc);
	}
}

class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}

	public String toString() {
		return "" + label;
	}
}
