package decodeWays;

//A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//Given an encoded message containing digits, determine the total number of ways to decode it.
//
//For example,
//Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
//
//The number of ways decoding "12" is 2.
public class Solution {
	// use a dp table dp[i] means from s[0 to i] how many ways to decode
	// dp[i] is calculated by dp[i-1] and dp[i-2]
	//  division would be
	// 			1) if s[i] != '0', then we have dp[i-1] ways( every split[i-1] plus a single s[i] )
	// 			2) if s[i-1] and s[i] can form a valid number (from 10 to 26) then we have another dp[i-2] ways( every split[i-2] plust s[i-1]s[i])
	// 			note we need to rule out when s[i] == 0 but s[i-1] == 1 or 2, in this case we only take 2), because '0' can't be used as a seperator decode
	public int numDecodings(String s) {
		if (s.length() == 0)
			return 0;
		if (s.charAt(0) == '0')
			return 0;
		if (s.length() == 1)
			return 1;
		int[] dp = new int[s.length() + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			if (s.charAt(i - 1) == '0') {
				if (s.charAt(i - 2) == '0' || s.charAt(i - 2) > '2') {
					return 0;
				} else {
					dp[i] = dp[i - 2];
				}
			} else {
				if (s.charAt(i - 2) == '0'
						|| Integer.parseInt(s.substring(i - 2, i)) > 26) {
					dp[i] = dp[i - 1];
				} else {
					dp[i] = dp[i - 1] + dp[i - 2];
				}
			}
		}
		return dp[s.length()];
	}

	public static void main(String[] args) {
		System.out.println(new Solution().numDecodings("1234"));
	}

}
