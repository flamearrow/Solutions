package distinctSubsequences;

//Given a string S and a string T, count the number of distinct subsequences of T in S.
//
//A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//
//Here is an example:
//S = "rabbbit", T = "rabbit"
//
//Return 3.

// the description is bullshit
// translate: give a string S and a string T, find the # of ways to take some chars away from S so that it becomes T
// S = "rabbbit", T = "rabbit"
//  way no.1: (take out the second and third 'b' from S) = T
//  way no.2: (take out the first and third 'b' from S) = T
//  way no.3: (take out the first and second 'b' from S) = T
// 
public class Solution {
	// this is a edit distance variation
	// dp table dp[i][j] means the number of ways to translate from first i letters of S to first j letters of T
	//  calculate dp[i][j]:
	// 			if(S[j] == T[i]) then we have two choices
	//  			choice 1: we use S[j] and T[i] to match, then we have dp[i-1][j-1] ways
	//              choice 2: we don't use S[j] to match, then T[i] would need to match with S[j-1], then we have dp[i][j-1] ways
	// note we only need a one dimension dp table since each row is only depending on previous row
	
	// when filling with one array, fill it vertically
	public int numDistinct(String S, String T) {
		int[] dp = new int[T.length() + 1];
		dp[0] = 1;
		for (int i = 0; i < S.length(); i++) {
			for (int j = T.length() - 1; j >= 0; j--) {
				if (S.charAt(i) == T.charAt(j)) {
					if(dp[j] > 0)
						dp[j + 1] += dp[j];
				}
			}
		}
		return dp[T.length()];
	}
	public static void main(String[] args) {
		System.out.println(new Solution().numDistinct("rabbbit", "rabbit"));
	}
}
