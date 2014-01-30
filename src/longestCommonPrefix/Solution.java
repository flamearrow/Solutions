package longestCommonPrefix;

//Write a function to find the longest common prefix string amongst an array of strings.
public class Solution {
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
