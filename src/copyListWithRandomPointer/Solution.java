package copyListWithRandomPointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//
//Return a deep copy of the list. 
public class Solution {
	
	public RandomListNode copyRandomList3(RandomListNode head) {
		RandomListNode ret = null, cur = head;
		while (cur != null) {
			RandomListNode nextNode = new RandomListNode(cur.label);
			RandomListNode oriNext = cur.next;
			cur.next = nextNode;
			nextNode.next = oriNext;
			if (ret == null) {
				ret = nextNode;
			}
			cur = oriNext;
		}
		cur = head;

		// copy over the random pointers first
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}

		cur = head;
		RandomListNode prev = null;
		while (cur != null) {
			RandomListNode retNext = cur.next;
			cur.next = retNext.next;
			if (prev != null) {
				prev.next = retNext;
			}
			prev = retNext;
			cur = cur.next;
		}
		return ret;
	}
	
	// a fancier idea, no need to ues a map
	// suppose the original list is a->b->c->d with a.rand = d
	//  in first round, we copy the list using the next field
	//    a'->b'->c'->d'
	//  in second round, we link original list and new list
	//    a->a'->b->b'->c->c'->d->d'
	//  in third round, we can update a.rand to a'.rand in O(1) : a.rand.next will be a'.rand.ext
	//  then we recover the list
	public RandomListNode copyRandomList2(RandomListNode head) {
		RandomListNode ret = null, cur = head;
		// round 1st and 2nd
		while (cur != null) {
			if (ret == null) {
				ret = new RandomListNode(cur.label);
				RandomListNode tmp = cur.next;
				cur.next = ret;
				ret.next = tmp;
			} else {
				RandomListNode tmp = cur.next;
				cur.next = new RandomListNode(cur.label);
				cur.next.next = tmp;
			}
			cur = cur.next.next;
		}

		cur = head;
		// note 3rd and 4th can't be merged, if we update the next link for a previous node, 
		//  then cur.next.random = cur.random.next will be broken
		// 3rd round
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}

		// 4th round
		RandomListNode retCur = null;
		cur = head;
		while (cur != null) {
			if (retCur == null) {
				retCur = cur.next;
				cur.next = cur.next.next;
				cur = cur.next;
			} else {
				retCur.next = cur.next;
				cur.next = cur.next.next;
				retCur = retCur.next;
				cur = cur.next;
			}
		}
		return ret;

	}

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

		RandomListNode nc = new Solution().copyRandomList2(n0);
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
