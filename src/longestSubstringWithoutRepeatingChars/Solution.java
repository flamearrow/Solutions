package longestSubstringWithoutRepeatingChars;

//Given a string, find the length of the longest substring without repeating characters. 
//For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
//which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
public class Solution {
	public int lengthOfLongestSubstring(String s) {
		boolean[] seen = new boolean[26];
		int start = 0, end = 0;
		int ret = 0;
		while (end < s.length()) {
			// we can advance the pointer, might seen longer sequence
			if (!seen[s.charAt(end) - 'a']) {
				seen[s.charAt(end) - 'a'] = true;
				if (ret < end - start + 1)
					ret = end - start + 1;
				end++;
			} else {
				// clear the mask
				// we can't go all the way to end, in case of abacd, once we find the second a, we should move start to b
				while (s.charAt(start) != s.charAt(end)) {
					seen[s.charAt(start) - 'a'] = false;
					start++;
				}
				start++;
				end++;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().lengthOfLongestSubstring(""));
	}

}
