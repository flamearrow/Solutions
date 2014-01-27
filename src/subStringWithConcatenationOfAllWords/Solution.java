package subStringWithConcatenationOfAllWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
//
//For example, given:
//S: "barfoothefoobarman"
//L: ["foo", "bar"]
//
//You should return the indices: [0,9].
//(order does not matter).
public class Solution {
	// similar to char window, we need to use a map to map String and quote
	// then we need to start from 0 to wordLenth-1 to search for all
	// combinations
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Map<String, Integer> backMap = new HashMap<String, Integer>();
		for (String s : L) {
			if (backMap.containsKey(s))
				backMap.put(s, backMap.get(s) + 1);
			else
				backMap.put(s, 1);
		}
		int wordSize = L[0].length();
		int size = 0;
		for (int i = 0; i < wordSize; i++) {
			int start = i, end = i;
			size = 0;
			// use end to probe
			while (end < S.length() - wordSize + 1) {
				String newStr = S.substring(end, end + wordSize);
				if (backMap.containsKey(newStr) && backMap.get(newStr) > 0) {
					backMap.put(newStr, backMap.get(newStr) - 1);
					size++;
					if (size == L.length) {
						ret.add(start);
					}
					end += wordSize;
				} else {
					// the tail is a valid string, but there's no quota, shrink
					// start hopefully can save some quote for this
					if (backMap.containsKey(newStr)) {
						String oldStr = S.substring(start, start + wordSize);
						backMap.put(oldStr, backMap.get(oldStr) + 1);
						start += wordSize;
						size--;
					}
					// the tail is a invalid string, skip this, start from after
					// that
					else {
						while (start < end) {
							String oldStr = S
									.substring(start, start + wordSize);
							backMap.put(oldStr, backMap.get(oldStr) + 1);
							start += wordSize;
						}
						end += wordSize;
						start = end;
						size = 0;
					}
				}
			}
			// need to shrink the window to recover map
			while (start < end) {
				String oldStr = S.substring(start, start + wordSize);
				backMap.put(oldStr, backMap.get(oldStr) + 1);
				start += wordSize;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		String s = "abaababbaba";
		String[] L = { "ab", "ba", "ab", "ba" };
		ArrayList<Integer> ret = new Solution().findSubstring(s, L);
		System.out.println(ret);
	}
}
