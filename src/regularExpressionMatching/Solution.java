package regularExpressionMatching;

//Implement regular expression matching with support for '.' and '*'
//
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
//
//The matching should cover the entire input string (not partial)
//
//The function prototype should be:
//bool isMatch(const char *s, const char *p)
//
//Some examples:
//isMatch("aa","a") -> false
//isMatch("aa","aa") -> true
//isMatch("aaa","aa") -> false
//isMatch("aa", "a*") -> true
//isMatch("aa", ".*") -> true
//isMatch("ab", ".*") -> true
//isMatch("aab", "c*a*b") -> true

public class Solution {

	public static void main(String[] args) {
		// isMatch("aa","a") -> false
		// isMatch("aa","aa") -> true
		// isMatch("aaa","aa") -> false
		// isMatch("aa", "a*") -> true
		// isMatch("aa", ".*") -> true
		// isMatch("ab", ".*") -> true
		// isMatch("aab", "c*a*b") -> true

		String s = "";
		String p = "c*a*";
		System.out.println(new Solution().isMatchDP(s, p));
	}

	// regex dp is simpler than wildcard dp
	// basicly if there's a star in a row, once we find a 1 in this row, all rest would in this row would be 1
	//  we set 1 for a start at dp[i][j] if (dp[i-1][j] || dp[i-1][j-1])
	// TODO: "aaa", "ab*a" doesn't pass
	public boolean isMatchDP(String s, String p) {
		if (s.length() == 0) {
			if (p.length() == 0)
				return true;
			else {
				int cur = 0;
				while (cur < p.length() - 1 && p.charAt(cur + 1) == '*')
					cur += 2;
				if (cur == p.length())
					return true;
				else
					return false;
			}
		}
		if (p.length() == 0)
			return false;
		int height = p.length();
		int width = s.length();
		boolean dp[][] = new boolean[height][width];
		// specially handle the first column and column
		int cur = 0;
		// handle the following case:
		// p=a*b*c*Zd*e*f* s=Z
		// this should match
		while (cur < p.length() - 1 && p.charAt(cur + 1) == '*') {
			cur += 2;
		}
		if (cur < p.length() && p.charAt(cur) == s.charAt(0)) {
			cur += 1;
		}
		while (cur < p.length() - 1 && p.charAt(cur + 1) == '*') {
			cur += 2;
		}
		for (int i = 0; i < cur; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				if (p.charAt(i) == '.' || s.charAt(j) == p.charAt(i)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(i) == '*') {
					dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
				}
			}
		}
		return dp[height - 1][width - 1];
	}

	public boolean isMatch3(String s, String p) {
		return doMatch3(s, p, 0, 0);
	}

	boolean doMatch3(String s, String p, int sPtr, int pPtr) {
		if (sPtr == s.length() && pPtr == p.length())
			return true;
		if (pPtr == p.length())
			return false;
		// handle (a, ab*c*d*)
		if (sPtr == s.length()) {
			while (pPtr < p.length() - 1 && p.charAt(pPtr + 1) == '*') {
				pPtr += 2;
			}
			if (pPtr == p.length())
				return true;
			return false;
		}
		// if a '*' follows
		if (pPtr < p.length() - 1 && p.charAt(pPtr + 1) == '*') {
			// don't match current
			if (doMatch3(s, p, sPtr, pPtr + 2))
				return true;
			// use current to match and begin from next one
			// the idea is to start matching from the next one in S if we can continue probing
			while (sPtr < s.length()
					&& (s.charAt(sPtr) == p.charAt(pPtr) || p.charAt(pPtr) == '.')) {
				if (doMatch3(s, p, sPtr + 1, pPtr + 2))
					return true;
				sPtr++;
			}
			return false;
		}
		// if no '*' follows
		else {
			if (p.charAt(pPtr) == '.' || p.charAt(pPtr) == s.charAt(sPtr)) {
				return doMatch3(s, p, sPtr + 1, pPtr + 1);
			} else {
				return false;
			}
		}
	}

	public boolean isMatch2(String s, String p) {
		return doMatch2(s, p, 0, 0);
	}

	boolean doMatch2(String s, String p, int sP, int pP) {
		if (sP == s.length() && pP == p.length())
			return true;
		// pattern ends but string doesn't end, false
		if (pP >= p.length())
			return false;
		if ((pP == p.length() - 1) || (pP + 1) < p.length()
				&& p.charAt(pP + 1) != '*') {
			// string ends, patten doesn't end with star, false
			if (sP >= s.length())
				return false;
			return matches(s.charAt(sP), p.charAt(pP))
					&& doMatch2(s, p, sP + 1, pP + 1);
		}
		// we find a star
		else {
			if (doMatch2(s, p, sP, pP + 2))
				return true;
			while (sP < s.length() && matches(s.charAt(sP), p.charAt(pP))) {
				sP++;
				if (doMatch2(s, p, sP, pP + 2))
					return true;
			}
			return false;
		}
	}

	// naive recursion
	// note the tricky edge case is when s hits tail
	// but p is still at a start position
	// i.e s="a" p="aa*b*c*d*"
	// we need to skip the start position and keep sPtr the same
	public boolean isMatch(String s, String p) {
		return doMatching(s, p, 0, 0);
	}

	boolean doMatching(String s, String p, int sPtr, int pPtr) {
		if (sPtr == s.length() && pPtr == p.length())
			return true;
		// edge case: when we hit s tail and p is at .*
		if (sPtr == s.length() && (pPtr < p.length() - 1)
				&& p.charAt(pPtr + 1) == '*') {
			return doMatching(s, p, sPtr, pPtr + 2);
		}
		if (sPtr == s.length() || pPtr == p.length())
			return false;
		if (pPtr < (p.length() - 1)) {
			// match one char
			if (p.charAt(pPtr + 1) != '*') {
				if (matches(s.charAt(sPtr), p.charAt(pPtr)))
					return doMatching(s, p, sPtr + 1, pPtr + 1);
				else
					return false;
			}
			// try match multiple
			else {
				// first try skip the start
				boolean success = doMatching(s, p, sPtr, pPtr + 2);
				if (success)
					return true;
				// then try match possible combinations
				while (sPtr < s.length()
						&& matches(s.charAt(sPtr), p.charAt(pPtr))) {
					success = doMatching(s, p, sPtr + 1, pPtr + 2);
					if (success)
						return true;
					sPtr++;
				}
				return false;
			}
		} else {
			if (matches(s.charAt(sPtr), p.charAt(pPtr)))
				return doMatching(s, p, sPtr + 1, pPtr + 1);
			else
				return false;
		}
	}

	boolean matches(char s, char p) {
		if (p == '.')
			return true;
		else
			return s == p;
	}
}
