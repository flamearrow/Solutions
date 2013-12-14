package palindromePartitioning2;

//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//Return the minimum cuts needed for a palindrome partitioning of s.
//
//For example, given s = "aab",
//Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
public class Solution {
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
		System.out.println(new Solution().minCut("aabcde"));
	}
}
