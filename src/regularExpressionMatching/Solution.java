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

	public static void main(String[] args) {
		// isMatch("aa","a") -> false
		// isMatch("aa","aa") -> true
		// isMatch("aaa","aa") -> false
		// isMatch("aa", "a*") -> true
		// isMatch("aa", ".*") -> true
		// isMatch("ab", ".*") -> true
		// isMatch("aab", "c*a*b") -> true

		String s = "";
		String p = "a*b*";
		System.out.println(new Solution().isMatch(s, p));
	}
}
