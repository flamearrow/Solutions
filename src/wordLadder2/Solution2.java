package wordLadder2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, 
// such that:
//
//    Only one letter can be changed at a time
//    Each intermediate word must exist in the dictionary
//
//For example,
//
//Given:
//start = "hit"
//end = "cog"
//dict = ["hot","dot","dog","lot","log"]
//
//Return
//
//  [
//    ["hit","hot","dot","dog","cog"],
//    ["hit","hot","lot","log","cog"]
//  ]
//
//Note:
//
//    All words have the same length.
//    All words contain only lowercase alphabetic characters.

public class Solution2 {
	//doesn't pass oj because TLE, but shows the idea
	public static void main(String[] args) {
		String start = "hit", end = "cog";
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");

		System.out.println(new Solution2().findLaddersMap(start, end, dict));
	}

	public List<List<String>> findLaddersMap(String start, String end,
			Set<String> dict) {
		Queue<String> q = new LinkedList<String>();
		int left = 1;
		// <node, parents>
		Map<String, Set<String>> backMap = new HashMap<String, Set<String>>();
		q.offer(start);
		boolean stopAtThislvl = false;
		dict.add(end);
		int lvl = 1;
		while (!q.isEmpty()) {
			String next = q.poll();
			for (String child : getChildren(next, dict)) {
				q.offer(child);
				if (backMap.containsKey(child)) {
					backMap.get(child).add(next);
				} else {
					Set<String> parents = new HashSet<String>();
					parents.add(next);
					backMap.put(child, parents);
				}
				if (child.equals(end)) {
					stopAtThislvl = true;
				}
			}
			dict.remove(next);
			left--;
			if (left == 0) {
				if (stopAtThislvl)
					break;
				else {
					lvl++;
					left = q.size();
				}
			}
		}
		// now we have stop at the shalowest level from start to end, need to rebuild the paths from end to start
		List<List<String>> ret = new LinkedList<List<String>>();
		LinkedList<String> curPath = new LinkedList<String>();
		curPath.add(end);
		buildResult(ret, backMap, start, end, end, curPath, lvl + 1);
		return ret;
	}

	void buildResult(List<List<String>> ret, Map<String, Set<String>> backMap,
			String start, String end, String current,
			LinkedList<String> curPath, int lvl) {
		if (curPath.size() > lvl)
			return;
		if (current.equals(start)) {
			ret.add(new ArrayList<String>(curPath));
		} else {
			if (backMap.containsKey(current)) {
				for (String parent : backMap.get(current)) {
					curPath.addFirst(parent);
					buildResult(ret, backMap, start, end, parent, curPath, lvl);
					curPath.removeFirst();
				}
			}
		}
	}

	public List<List<String>> findLadders(String start, String end,
			Set<String> dict) {
		Queue<Node> q = new LinkedList<Node>();
		int left = 1;
		q.offer(new Node(start, null));
		boolean stopAtThislvl = false;
		List<Node> endNodes = new LinkedList<Node>();
		dict.add(end);
		while (!q.isEmpty()) {
			Node next = q.poll();
			for (String child : getChildren(next.s, dict)) {
				Node newNode = new Node(child, next);
				q.offer(newNode);
				if (child.equals(end)) {
					stopAtThislvl = true;
					endNodes.add(newNode);
				}
			}
			dict.remove(next.s);
			left--;
			if (left == 0) {
				if (stopAtThislvl)
					break;
				else
					left = q.size();
			}
		}
		// now we have stop at the shalowest level from start to end, need to rebuild the paths from end to start
		List<List<String>> ret = new LinkedList<List<String>>();
		for (Node n : endNodes) {
			LinkedList<String> l = new LinkedList<String>();
			while (n.prev != null) {
				l.addFirst(n.s);
				n = n.prev;
			}
			l.addFirst(start);
			ret.add(l);
		}

		return ret;
	}

	List<String> getChildren(String next, Set<String> dict) {
		List<String> ret = new LinkedList<String>();
		int wordLen = next.length();
		for (int i = 0; i < wordLen; i++) {
			for (int j = 0; j < 26; j++) {
				String prob = next.substring(0, i) + (char) ('a' + j)
						+ next.substring(i + 1);
				if (dict.contains(prob) && !prob.equals(next))
					ret.add(prob);
			}
		}
		return ret;
	}

	static class Node {
		String s;
		Node prev;

		public Node(String s, Node prev) {
			super();
			this.s = s;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return s;
		}
	}
}
