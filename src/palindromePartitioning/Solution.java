package palindromePartitioning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//Return all possible palindrome partitioning of s.
//
//For example, given s = "aab",
//Return
//
// [
//   ["aa","b"],
//   ["a","a","b"]
// ]

public class Solution {
	// dp, no recurse, create an array of linked list to store break points
	private static class Node {
		Node next;
		int val;

		public Node(int argVal) {
			val = argVal;
		}
	}

	public ArrayList<ArrayList<String>> partition2(String s) {
		Node[] nodes = new Node[s.length()];
		doProbe(s, nodes);
		return buildResults(nodes, s);
	}

	void doProbe(String s, Node[] nodes) {
		for (int start = 0; start < s.length(); start++) {
			for (int end = start; end < s.length(); end++) {
				if (isPalindrome(s, start, end)) {
					if (nodes[end] != null) {
						Node newNode = new Node(start);
						newNode.next = nodes[end];
						nodes[end] = newNode;
					} else {
						nodes[end] = new Node(start);
					}
				}
			}
		}
	}

	ArrayList<ArrayList<String>> buildResults(Node[] nodes, String s) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		LinkedList<String> cur = new LinkedList<String>();
		probeResult(ret, cur, nodes, nodes.length - 1, s);
		return ret;
	}

	void probeResult(ArrayList<ArrayList<String>> ret, LinkedList<String> cur,
			Node[] nodes, int index, String s) {
		if (index == -1) {
			ret.add(new ArrayList<String>(cur));
		} else {
			Node n = nodes[index];
			while (n != null) {
				String prev = s.substring(n.val, index + 1);
				cur.addFirst(prev);
				probeResult(ret, cur, nodes, n.val - 1, s);
				cur.removeFirst();
				n = n.next;
			}
		}
	}

	// a naive approach: start from 0, prob the first pld, then second ...
	// 	all the way to end, then recurse
	// use a stack to back trace
	public ArrayList<ArrayList<String>> partition(String s) {
		Stack<String> currentStrs = new Stack<String>();
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		probe(s, 0, currentStrs, ret);
		return ret;
	}

	void probe(String s, int from, Stack<String> currentStrs,
			ArrayList<ArrayList<String>> rst) {
		for (int to = from; to < s.length(); to++) {
			if (isPalindrome(s, from, to)) {
				currentStrs.push(s.substring(from, to + 1));
				// find a full split
				if (to == s.length() - 1) {
					ArrayList<String> newStr = new ArrayList<String>(
							currentStrs);
					rst.add(newStr);
				}
				// need to probe further
				else {
					probe(s, to + 1, currentStrs, rst);
				}
				currentStrs.pop();
			}
		}
	}

	boolean isPalindrome(String s, int from, int to) {
		while (from != to && (from != to - 1)) {
			if (s.charAt(from) != s.charAt(to)) {
				return false;
			}
			from++;
			to--;
		}
		// even
		if (from == to - 1) {
			return s.charAt(from) == s.charAt(to);
		}
		// odd
		else {
			return true;
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int i = new Solution().partition("aaaaaaaaaaaaaaaa").size();
		System.out.println(i + " results, recursion takes "
				+ (System.currentTimeMillis() - start) + " millis");
		long start2 = System.currentTimeMillis();
		int i2 = new Solution().partition2("aaaaaaaaaaaaaaaa").size();
		System.out.println(i2 + " results, dp takes "
				+ (System.currentTimeMillis() - start2) + " millis");
	}
}
