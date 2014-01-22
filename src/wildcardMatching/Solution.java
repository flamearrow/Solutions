package wildcardMatching;

//Implement wildcard pattern matching with support for '?' and '*'.
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
//
//The matching should cover the entire input string (not partial).
//
//The function prototype should be:
//bool isMatch(const char *s, const char *p)
//
//Some examples:
//isMatch("aa","a")  false
//isMatch("aa","aa")  true
//isMatch("aaa","aa")  false
//isMatch("aa", "*")  true
//isMatch("aa", "a*")  true
//isMatch("ab", "?*")  true
//isMatch("aab", "c*a*b")  false
public class Solution {

	// do a 2D dp, O(mn)
	public boolean isMatch(String s, String p) {
		if (s.length() != 0 && p.length() == 0)
			return false;
		int i = 0;
		while (i < p.length() && p.charAt(i) == '*') {
			i++;
		}
		if (i == p.length())
			return true;

		int plenNoStar = 0;
		for (char c : p.toCharArray())
			if (c != '*')
				plenNoStar++;
		if (plenNoStar > s.length())
			return false;

		boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

		dp[0][0] = true;
		for (i = 1; i < dp.length; i++) {
			if (p.charAt(i - 1) == '*' && dp[i - 1][0])
				dp[i][0] = true;
		}
		// dp[i][j] means if the first j chars of s
		// matches the first i chars of p
		for (i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				// if it's star, then we search all previous blocks of current
				// row
				// and all previous blocks of previous row
				// and the upper cell
				// once any is true, current is true
				if (p.charAt(i - 1) == '*') {
					int ptr = j - 1;
					while (ptr >= 0) {
						if (dp[i][ptr]) {
							dp[i][j] = true;
							break;
						}
						ptr--;
					}
					if (!dp[i][j]) {
						ptr = j;
						while (ptr >= 0) {
							if (dp[i - 1][ptr]) {
								dp[i][j] = true;
								break;
							}
							ptr--;
						}
					}
				}
				// if current char matches or current pattern is ?, then if
				// previous matches(dp[i-1][j-1]), current matches
				else if (s.charAt(j - 1) == p.charAt(i - 1)
						|| p.charAt(i - 1) == '?') {
					dp[i][j] = dp[i - 1][j - 1];
				}
				// otherwise, p.charAt(i) is not * or ?
				// and s.charAt(j) != p.charAt(i)
				else {
					dp[i][j] = false;
				}
			}
		}

		return dp[p.length()][s.length()];
	}

	// this recursive one is intuitive but requires factorial time
	public boolean isMatchRec(String s, String p) {
		return doMatch(s, 0, p, 0);
	}

	// starting matching s from indexS and p from indexP
	boolean doMatch(String s, int indexS, String p, int indexP) {
		if (indexS == s.length() && indexP == p.length())
			return true;
		else if (indexS == s.length() || indexP == p.length()) {
			return false;
		}
		if (p.charAt(indexP) == '?' || s.charAt(indexS) == p.charAt(indexP)) {
			return doMatch(s, indexS + 1, p, indexP + 1);
		} else if (p.charAt(indexP) == '*') {
			// skip duplicate stars
			while (indexP < p.length() && p.charAt(indexP) == '*') {
				indexP++;
			}
			for (int i = indexS; i <= s.length(); i++) {
				if (doMatch(s, i, p, indexP)) {
					return true;
				}
			}
			return false;
		}
		// s.charAt(indexS) != p.charAt(indexP)
		// and p.charAt(indexP) is not special char
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		// System.out.println(new Solution().isMatch("", "**"));
		System.out.println(new Solution().isMatch("hi", "*?"));
	}
}