package reverseWordsInAString;

//Given an input string, reverse the string word by word.
//
//For example,
//Given s = "the sky is blue",
//return "blue is sky the".
//
//Clarification:
//
//   What constitutes a word?
//   A sequence of non-space characters constitutes a word.
//   Could the input string contain leading or trailing spaces?
//   Yes. However, your reversed string should not contain leading or trailing spaces.
//   How about multiple spaces between two words?
//   Reduce them to a single space in the reversed string.

public class Solution {
	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		int cur = 0, prob = 0;
		while (cur != s.length()) {
			if (s.charAt(cur) == ' ') {
				cur++;
				continue;
			}
			prob = cur;
			while (prob != s.length() && s.charAt(prob) != ' ')
				prob++;
			if (first) {
				sb.insert(0, s.substring(cur, prob));
				first = false;
			} else {
				sb.insert(0, ' ');
				sb.insert(0, s.substring(cur, prob));
			}
			cur = prob;
		}
		return sb.toString();
	}
}
