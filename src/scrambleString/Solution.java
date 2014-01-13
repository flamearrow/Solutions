package scrambleString;

//Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
//
//Below is one possible representation of s1 = "great":
//
//    great
//   /    \
//  gr    eat
// / \    /  \
//g   r  e   at
//           / \
//          a   t
//To scramble the string, we may choose any non-leaf node and swap its two children.
//
//For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
//
//    rgeat
//   /    \
//  rg    eat
// / \    /  \
//r   g  e   at
//           / \
//          a   t
//We say that "rgeat" is a scrambled string of "great".
//
//Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
//
//    rgtae
//   /    \
//  rg    tae
// / \    /  \
//r   g  ta  e
//       / \
//      t   a
//We say that "rgtae" is a scrambled string of "great".
//
//Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
public class Solution {
	public boolean isScramble(String s1, String s2) {
		if (s1.equals(s2))
			return true;
		int[] charOcc = new int[26];
		// check if they have same chars
		for (int i = 0; i < s1.length(); i++) {
			charOcc[s1.charAt(i) - 'a']++;
			charOcc[s2.charAt(i) - 'a']--;
		}
		for (int i : charOcc) {
			if (i != 0)
				return false;
		}

		// try all combinations
		for (int i = 0; i < s1.length() - 1; i++) {
			String s1Left = s1.substring(0, i + 1);
			String s1Right = s1.substring(i + 1);
			String s2Left = s2.substring(0, s2.length() - 1 - i);
			String s2Right = s2.substring(s2.length() - 1 - i);
			boolean rst = isScramble(s1Left, s2Right)
					&& isScramble(s1Right, s2Left);
			if (rst)
				return true;

			s2Left = s2.substring(0, i + 1);
			s2Right = s2.substring(i + 1);
			rst = isScramble(s1Left, s2Left) && isScramble(s1Right, s2Right);
			if (rst)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().isScramble("abcd", "bdac"));
	}

}
