package palindromePartitioning2;

import java.util.Arrays;

//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//Return the minimum cuts needed for a palindrome partitioning of s.
//
//For example, given s = "aab",
//Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
public class Solution {
	// to make isPalindrome constant, we use a 2D array.
	// each inner loop will check palindrome from shortest end
	public int minCut3(String s) {
		boolean[][] backSet = new boolean[s.length()][s.length()];
		int[] ret = new int[s.length() + 1];
		Arrays.fill(ret, Integer.MAX_VALUE);
		ret[0] = -1;
		for (int i = 1; i <= s.length(); i++) {
			int minCut = Integer.MAX_VALUE;
			// ret[j-1] is the minCut of s[0-j] inclusive
			for (int j = i - 1; j >= 0; j--) {
				// check if s[j to i-1] is palindrome, note (i-1-j) would grow from 0 to i-1 so that we always check shorter strings first
				// i-j <= 2 corresponds to
				//		1) j == i-1 : same char
				//		2) j == i-1-1 : consecutive char
				// if they're not , then just strip out head and tail of current string for subproblem checking, i.e j+1 and i-1-1
				if ((s.charAt(j) == s.charAt(i - 1) && ((i - j <= 2) || backSet[j + 1][i - 2]))) {
					backSet[j][i - 1] = true;
				}
				if (ret[j] < Integer.MAX_VALUE && backSet[j][i - 1]) {
					minCut = Math.min(ret[j] + 1, minCut);
				}
			}
			ret[i] = minCut;
		}
		return ret[s.length()];
	}

	public int minCut2(String s) {
		boolean[][] backSet = new boolean[s.length()][s.length()];
		int[] ret = new int[s.length() + 1];
		Arrays.fill(ret, Integer.MAX_VALUE);
		ret[0] = -1;
		for (int i = 1; i <= s.length(); i++) {
			int minCut = Integer.MAX_VALUE;
			// ret[j-1] is the minCut of s[0-j] inclusive
			for (int j = 0; j < i; j++) {
				if (ret[j] < Integer.MAX_VALUE
						&& isPalindrome2(s, j, i - 1, backSet)) {
					minCut = Math.min(ret[j] + 1, minCut);
				}
			}
			ret[i] = minCut;
		}
		return ret[s.length()];
	}

	boolean isPalindromeNaive(String s) {
		int start = 0, end = s.length() - 1;
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return false;
			} else {
				start++;
				end--;
			}
		}
		return true;
	}

	// check if s[start to end] inclusive is a palindrome
	private boolean isPalindrome2(String s, int start, int end,
			boolean[][] backSet) {
		if (start == end) {
			backSet[start][end] = true;
			return true;
		}

		int bStart = start, bEnd = end;
		while (start < end) {
			if (backSet[start][end]) {
				backSet[bStart][bEnd] = true;
				return true;
			}
			if (s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
			} else {
				backSet[bStart][bEnd] = false;
				return false;
			}
		}

		while (bStart < bEnd) {
			backSet[bStart][bEnd] = true;
			bStart++;
			bEnd--;
		}
		return true;

	}

	boolean[][] backSet;

	// using a O(n^2) dp approach,
	// use an array to record min cut for a[0] - a[i]

	// note: check isPalindrome can be made O(1) by
	// backing up the existing i,j and only check if s[i-1] == s[j+1]
	public int minCut(String s) {
		backSet = new boolean[s.length()][s.length()];
		if (s.length() == 0)
			return 0;
		int[] dp = new int[s.length()];
		dp[0] = 0;
		for (int i = 1; i < s.length(); i++) {
			if (isPalindrome(s, 0, i)) {
				dp[i] = 0;
			} else {
				int tmp = Integer.MAX_VALUE;
				for (int j = 1; j <= i; j++) {
					if (isPalindrome(s, j, i)) {
						if (dp[j - 1] + 1 < tmp)
							tmp = dp[j - 1] + 1;
					} else {
						if (dp[j - 1] + i - j + 1 < tmp)
							tmp = dp[j - 1] + i - j + 1;
					}
				}
				dp[i] = tmp;
			}
		}
		return dp[s.length() - 1];
	}

	boolean isPalindrome(String s, int from, int to) {
		if (backSet[from][to])
			return true;
		if ((from < s.length() - 1) && (to > 1) && backSet[from + 1][to - 1]
				&& (s.charAt(from) == s.charAt(to))) {
			backSet[from][to] = true;
			return true;
		}
		while (from != to && (from != to - 1)) {
			if (s.charAt(from) != s.charAt(to)) {
				return false;
			}
			from++;
			to--;
		}
		// even
		if (from == to - 1) {
			if (s.charAt(from) == s.charAt(to)) {
				backSet[from][to] = true;
				return true;
			} else {
				return false;
			}
		}
		// odd
		else {
			backSet[from][to] = true;
			return true;
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().minCut3("aaab"));
	}
}
