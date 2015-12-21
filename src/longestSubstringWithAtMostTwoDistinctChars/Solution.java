package longestSubstringWithAtMostTwoDistinctChars;

import java.util.HashMap;
import java.util.Map;

//Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
//
//For example, Given s = “eceba”,
//
//T is "ece" which its length is 3.
public class Solution {
	// substring needs to be consecutive
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int start = 0, end = 0;
		Map<Character, Integer> charCounts = new HashMap<>();
		int maxCount = 0;
		while (start < s.length() && end < s.length()) {
			increment(charCounts, s.charAt(end));
			end++;
			while (charCounts.size() > 2) {
				decrement(charCounts, s.charAt(start));
				start++;
			}
			int curCount = end - start;
			if (curCount > maxCount) {
				maxCount = curCount;
			}
		}
		return maxCount;
	}

	void increment(Map<Character, Integer> charCounts, char c) {
		if (charCounts.containsKey(c)) {
			charCounts.put(c, charCounts.get(c) + 1);
		} else {
			charCounts.put(c, 1);
		}
	}

	void decrement(Map<Character, Integer> charCounts, char c) {
		if (charCounts.get(c) == 1) {
			charCounts.remove(c);
		} else {
			charCounts.put(c, charCounts.get(c) - 1);
		}
	}
}
