package editDistance;

//Given two words word1 and word2, find the minimum number of steps required 
// to convert word1 to word2. (each operation is counted as 1 step.)
//
//You have the following 3 operations permitted on a word:
//
//a) Insert a character
//b) Delete a character
//c) Replace a character
public class Solution {

	public int minDistance2(String word1, String word2) {
		int l1 = word1.length();
		int l2 = word2.length();

		int[][] dp = new int[l1 + 1][l2 + 1];
		for (int i = 1; i <= l1; i++)
			dp[i][0] = i;
		for (int i = 1; i <= l2; i++)
			dp[0][i] = i;
		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				// add/remove
				int min = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					// equal
					min = Math.min(dp[i - 1][j - 1], min);
				} else {
					// replace
					min = Math.min(dp[i - 1][j - 1] + 1, min);
				}
				dp[i][j] = min;
			}
		}
		return dp[l1][l2];
	}

	public int minDistance(String word1, String word2) {
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i < word1.length() + 1; i++) {
			dp[i][0] = i;
		}

		for (int i = 0; i < word2.length() + 1; i++) {
			dp[0][i] = i;
		}
		// note we can take dp[i-1][j-1] as min candidate only when
		// word1.charAt(i-1) == word2.charAt(j-1)
		for (int i = 1; i < word1.length() + 1; i++) {
			for (int j = 1; j < word2.length() + 1; j++) {
				int remove = dp[i - 1][j] + 1;
				int add = dp[i][j - 1] + 1;
				int repOrEql = dp[i - 1][j - 1]
						+ (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
				dp[i][j] = remove < add ? remove < repOrEql ? remove : repOrEql
						: add < repOrEql ? add : repOrEql;
			}
		}
		return dp[word1.length()][word2.length()];
	}

	public static void main(String[] args) {
		System.out.println(new Solution().minDistance("saturday", "sunday"));
		System.out.println(new Solution().minDistance2("saturday", "sunday"));
	}
}
