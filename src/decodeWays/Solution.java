package decodeWays;

import java.util.HashMap;
import java.util.Map;

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
	// there are 26 characters
	Map<String, Integer> back = new HashMap<String, Integer>();

	// there are 26 characters
	// just a recursive call
	//  when s ends with 0, we can't recursively call s[0, s.length()-1]
	//  when s ends with 0x or 26+, we can't recursively call s[0, s.length()-2]
	// note we need to wrap a method to check "" for 0 for original S
	// and "" for 1 for recursive call
	public int numDecodings2(String s) {
		if (s == null || s.equals(""))
			return 0;
		return doRecur(s);
	}

	int doRecur(String s) {
		if (back.containsKey(s)) {
			return back.get(s);
		}
		if (s.length() == 0)
			return 1;
		if (s.length() == 1)
			return s.equals("0") ? 0 : 1;
		int ret = 0;
		ret += s.charAt(s.length() - 1) == '0' ? 0 : doRecur(s.substring(0,
				s.length() - 1));
		int lastTwoDigits = Integer.parseInt(s.substring(s.length() - 2,
				s.length()));
		if (lastTwoDigits < 10 || lastTwoDigits > 26)
			ret += 0;
		else
			ret += doRecur(s.substring(0, s.length() - 2));
		back.put(s, ret);
		return ret;
	}

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
		System.out.println(new Solution().numDecodings2("10"));
	}

}
