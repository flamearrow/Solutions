package wordBreak;

import java.util.HashSet;
import java.util.Set;

//Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
//For example, given
//s = "leetcode",
//dict = ["leet", "code"].
//
//Return true because "leetcode" can be segmented as "leet code".
public class Solution {
	Set<Integer> backSet = new HashSet<Integer>();

	public boolean wordBreakDP(String s, Set<String> dict) {
		// dp[i]: substring [0-i] can be broken or not
		// dp[i]-1: if there exists k from (0-i) such that dp[k] == true and
		// substring[i-i] is in dict, then it's true
		if (s == null || dict == null)
			return false;
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 0; i <= s.length(); i++) {
			for (int k = 0; k < i; k++) {
				if (dp[k] && dict.contains(s.substring(k, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}

	public boolean wordBreak(String s, Set<String> dict) {
		if (s == null)
			return false;
		char[] chars = s.toCharArray();
		int end = chars.length;
		return canBreak(0, end, chars, dict);
	}

	private boolean canBreak(int start, int end, char[] chars, Set<String> dict) {
		if (backSet.contains(start))
			return true;
		if (start == end) {
			backSet.add(start);
			return true;
		}
		for (int i = start + 1; i <= end; i++) {
			String sub = new String(chars, start, i - start);
			if (dict.contains(sub) && canBreak(i, end, chars, dict)) {
				backSet.add(start);
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "adaab";
		Set<String> dict = new HashSet<String>();
		dict.add("ad");
		dict.add("aab");

		System.out.println(new Solution().wordBreakDP(s, dict));
	}

}
