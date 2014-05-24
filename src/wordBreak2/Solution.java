package wordBreak2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution {
	
	public ArrayList<String> wordBreak2(String s, Set<String> dict) {
		Node[] nodes = new Node[s.length()];
		for (int i = 0; i < s.length(); i++) {
			if (dict.contains(s.substring(0, i + 1))) {
				addNewNode(nodes, i, -1);
			}
			for (int j = 0; j < i; j++) {
				if (nodes[j] != null
						&& dict.contains(s.substring(j + 1, i + 1))) {
					addNewNode(nodes, i, j);
				}
			}
		}
		return buildTree(s, nodes);
	}

	ArrayList<String> buildTree(String s, Node[] nodes) {
		ArrayList<String> ret = new ArrayList<String>();
		LinkedList<String> cur = new LinkedList<String>();
		doProbe(ret, nodes, nodes.length - 1, cur, s);
		return ret;
	}

	void doProbe(ArrayList<String> ret, Node[] nodes, int curIndex,
			LinkedList<String> cur, String s) {
		Node curNode = nodes[curIndex];
		while (curNode != null) {
			if (curNode.prevIndex == -1) {
				cur.add(s.substring(0, curIndex + 1));
				addToRst(cur, ret);
				cur.removeLast();
			} else {
				cur.add(s.substring(curNode.prevIndex + 1, curIndex + 1));
				doProbe(ret, nodes, curNode.prevIndex, cur, s);
				cur.removeLast();
			}
			curNode = curNode.next;
		}
	}

	void addToRst(LinkedList<String> cur, ArrayList<String> ret) {
		if (cur.size() == 1) {
			ret.add(cur.getFirst());
			return;
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String s : cur) {
			if (first) {
				sb.insert(0, s);
				first = false;
			} else {
				sb.insert(0, ' ');
				sb.insert(0, s);
			}
		}
		ret.add(sb.toString());
	}

	private void addNewNode(Node[] nodes, int index, int prev) {
		if (nodes[index] == null) {
			nodes[index] = new Node(prev);
		} else {
			Node cur = nodes[index];
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = new Node(prev);
		}
	}

	static class Node {
		int prevIndex = -1;
		Node next = null;

		public Node(int prevIndex) {
			this.prevIndex = prevIndex;
		}
	}
	
	public ArrayList<String> wordBreakRec(String s, Set<String> dict) {
		// the node in dp will represent going back n spot we can create A word
		// null if not able to create
		// linked list if there're a couple of nodes
		LinkedNode[] dp = new LinkedNode[s.length() + 1];
		dp[0] = new LinkedNode(-1);
		for (int i = 0; i <= s.length(); i++) {
			for (int k = 0; k < i; k++) {
				if (dp[k] != null && dict.contains(s.substring(k, i))) {
					LinkedNode newNode = new LinkedNode(i - k);
					newNode.next = dp[i];
					dp[i] = newNode;
				}
			}
		}

		ArrayList<String> ret = new ArrayList<String>();
		ArrayList<String> tmp = new ArrayList<String>();
		buildStrs(dp, s.length(), ret, tmp, s);
		return ret;
	}

	// build the array list according to dp table, starting from last index,
	// search like a DPS tree search
	private void buildStrs(LinkedNode[] nodes, int currentIndex,
			ArrayList<String> ret, ArrayList<String> tmp, String ori) {
		if (currentIndex == 0) {
			// reach head, need to add
			boolean justStart = true;
			StringBuilder sb = new StringBuilder();
			for (int i = tmp.size() - 1; i >= 0; i--) {
				String s = tmp.get(i);
				if (justStart) {
					justStart = false;
					sb.append(s);
				} else {
					sb.append(" ");
					sb.append(s);
				}
			}
			ret.add(sb.toString());
			return;
		}
		LinkedNode node = nodes[currentIndex];

		while (node != null) {
			String sub = ori.substring(currentIndex - node.val, currentIndex);
			tmp.add(sub);
			buildStrs(nodes, currentIndex - node.val, ret, tmp, ori);
			tmp.remove(tmp.size() - 1);
			node = node.next;
		}
	}

	class LinkedNode {
		int val;
		LinkedNode next;

		public LinkedNode(int val) {
			this.val = val;
		}
	}

	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		if (s == null || dict.size() == 0)
			return null;
		char[] chars = s.toCharArray();
		int end = chars.length;
		ArrayList<String> tmpArr = new ArrayList<String>();
		ArrayList<String> ret = new ArrayList<String>();
		probe(0, end, chars, dict, tmpArr, ret);
		return ret;
	}

	private void probe(int start, int end, char[] chars, Set<String> dict,
			ArrayList<String> tmpArr, ArrayList<String> ret) {
		if (start == end) {
			// reach end, can add
			StringBuilder sb = new StringBuilder();
			boolean justStart = true;
			for (String s : tmpArr) {
				if (justStart) {
					sb.append(s);
					justStart = false;
				} else {
					sb.append(" ");
					sb.append(s);
				}
			}
			ret.add(sb.toString());
			return;
		}
		for (int i = start + 1; i <= end; i++) {
			String sub = new String(chars, start, i - start);
			if (dict.contains(sub)) {
				// try to prob with current sub
				tmpArr.add(sub);
				probe(i, end, chars, dict, tmpArr, ret);
				// done probing with current sub, need to probe next
				tmpArr.remove(tmpArr.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		String ss = "catsanddog";
		Set<String> dict = new HashSet<String>();
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("dog");
		dict.add("sand");
		for (String s : new Solution().wordBreakRec(ss, dict)) {
			System.out.println(s);
		}
	}
}
