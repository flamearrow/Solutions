package maximumProductOfWordLengths;

import java.util.Arrays;
import java.util.Comparator;

//Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
// where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
//
//Example 1:
//Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
//Return 16
//The two words can be "abcw", "xtfn".
//
//Example 2:
//Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
//Return 4
//The two words can be "ab", "cd".
//
//Example 3:
//Given ["a", "aa", "aaa", "aaaa"]
//Return 0
//No such pair of words.
public class Solution {
	// Sort the shit by length, start form bigger ones
	public int maxProduct(String[] words) {
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});

		// funny way to check if two words have same chars - mask each char to a
		// bit from 26 bits
		int[] mask = new int[words.length];
		for (int i = 0; i < mask.length; i++) {
			String s = words[i];
			for (char c : s.toCharArray()) {
				mask[i] |= 1 << (c - 'a');
			}
		}

		// note the length is reversely sorted
		int max = 0;
		// N square seach shit
		for (int i = 0; i < words.length; i++) {
			// we're looking for len(i) * something smaller than len(i)
			// if len(i) squre isn't big enough, then there's no point looking
			// at i
			if (words[i].length() * words[i].length() < max) {
				break;
			}
			for (int j = 1; j < words.length; j++) {
				if ((mask[i] & mask[j]) == 0) {
					max = Math.max(max, words[i].length() * words[j].length());
					// similar here, continue looking will only see shorter
					// words[j] and shorter words[i]
					break;
				}
			}
		}
		return max;
	}
}
