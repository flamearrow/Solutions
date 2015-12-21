package oneEditDistance;

//Given two strings S and T, determine if they are both one edit distance apart.
public class Solution {
	public static void main(String[] args) {
		System.out.println(new Solution().isOneEditDistance("a", "aaa"));
	}

	// edit
	// plus
	// always assume len(s) > len(t)
	public boolean isOneEditDistance(String s, String t) {
		int lenS = s.length();
		int lenT = t.length();
		if (lenS < lenT) {
			return isOneEditDistance(t, s);
		}
		int diff = lenS - lenT;
		if (diff > 1) {
			return false;
		}
		int i = 0;
		while (i < lenT && s.charAt(i) == t.charAt(i)) {
			i++;
		}
		// same string
		if (diff == 0 && i == lenT) {
			return false;
		}
		int indexS = 0, indexT = 0;
		if (diff == 1) {
			indexS = i + 1;
			indexT = i;
		} else {
			indexS = i + 1;
			indexT = i + 1;
		}
		while (indexT < t.length() && s.charAt(indexS) == t.charAt(indexT)) {
			indexS++;
			indexT++;
		}
		return indexS == s.length();
	}
}
