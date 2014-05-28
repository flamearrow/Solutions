package longestCommonPrefix;

//Write a function to find the longest common prefix string amongst an array of strings.
public class Solution {
	public String longestCommonPrefix2(String[] strs) {
        	if (strs.length == 0)
			return "";
		String ret = strs[0];
		for (int i = 1; i < strs.length; i++) {
			ret = prefix(ret, strs[i]);
		}
		return ret;
	}

	String prefix(String s1, String s2) {
		StringBuilder sb = new StringBuilder();
		int cur1 = 0, cur2 = 0;
		while (cur1 < s1.length() && cur2 < s2.length()) {
			if (s1.charAt(cur1) == s2.charAt(cur2)) {
				sb.append(s1.charAt(cur1));
				cur1++;
				cur2++;
			} else {
				break;
			}
		}
		return sb.toString();
	}
	// no trie, do it naively
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String ret = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int cur = 0;
			while (cur < ret.length() && cur < strs[i].length()) {
				if (ret.charAt(cur) == strs[i].charAt(cur))
					cur++;
				else
					break;
			}
			ret = ret.substring(0, cur);
		}
		return ret;
	}

	public static void main(String[] args) {
		String[] strs = { "abca" };
		System.out.println(new Solution().longestCommonPrefix(strs));
	}
}
