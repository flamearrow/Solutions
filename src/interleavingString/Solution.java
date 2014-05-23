package interleavingString;

//Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
//
//For example,
//Given:
//s1 = "aabcc",
//s2 = "dbbca",
//
//When s3 = "aadbbcbcac", return true.
//When s3 = "aadbbbaccc", return false.
public class Solution {
	public boolean isInterleave2(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length())
			return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;
		int cur = 1;
		//note for the first column and row of dp, we need to match the longest prefix
		while (cur <= s1.length() && s1.charAt(cur - 1) == s3.charAt(cur - 1)) {
			dp[cur][0] = true;
			cur++;
		}
		cur = 1;
		while (cur <= s2.length() && s2.charAt(cur - 1) == s3.charAt(cur - 1)) {
			dp[0][cur] = true;
			cur++;
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
						|| dp[i][j - 1]
						&& s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
					dp[i][j] = true;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
	// dp
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length())
			return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		// dp[i][j] means s1[0 to i-1] and s2[0 to j-1] can interleave to s3[0
		// to i+j-1]
		dp[0][0] = true;
		for (int i = 1; i <= s1.length(); i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0]) {
				dp[i][0] = true;
			}
		}

		for (int j = 1; j <= s2.length(); j++) {
			if (s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1]) {
				dp[0][j] = true;
			}
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if ((dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
						|| (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j
								- 1))) {
					dp[i][j] = true;
				}
			}
		}

		return dp[s1.length()][s2.length()];
	}

	// a recursive binary tree probe solution
	public boolean isInterleaveRec(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length())
			return false;
		return probe(s1, s2, s3, 0, 0, 0);
	}

	boolean probe(String s1, String s2, String s3, int index1, int index2,
			int index3) {
		if (index3 == s3.length())
			return true;
		char c3 = s3.charAt(index3);
		if (index1 < s1.length()) {
			char c1 = s1.charAt(index1);
			if (c1 == c3 && probe(s1, s2, s3, index1 + 1, index2, index3 + 1))
				return true;
		}
		if (index2 < s2.length()) {
			char c2 = s2.charAt(index2);
			if (c2 == c3 && probe(s1, s2, s3, index1, index2 + 1, index3 + 1))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// String s1 = "a";
		// String s2 = "";
		// String s3 = "a";
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		boolean rst = new Solution().isInterleave(s1, s2, s3);
		System.out.println(rst);
	}
}
