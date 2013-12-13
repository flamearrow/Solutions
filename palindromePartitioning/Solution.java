package palindromePartitioning;

import java.util.ArrayList;
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
	// a kinda naive approach: start from 0, prob the first pld, then second ...
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
		for (ArrayList<String> list : new Solution().partition("asdffdsaabcba")) {
			for (String s : list) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}
}
