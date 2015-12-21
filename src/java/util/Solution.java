package java.util;

//Given an input string, reverse the string word by word. 
//A word is defined as a sequence of non-space characters.
//
//The input string does not contain leading or trailing spaces 
//and the words are always separated by a single space.
//
//For example,
//Given s = "the sky is blue",
//return "blue is sky the".
public class Solution {
	public void reverseWords(char[] s) {
		reverse(s, 0, s.length - 1);
		int p = 0;
		while (p < s.length) {
			int cur = p;
			while (p < s.length && s[p] != ' ') {
				p++;
			}
			reverse(s, cur, p - 1);
			p++;
		}
	}

	void reverse(char[] s, int start, int end) {
		while (start < end) {
			char c = s[start];
			s[start] = s[end];
			s[end] = c;
			start++;
			end--;
		}
	}
}
