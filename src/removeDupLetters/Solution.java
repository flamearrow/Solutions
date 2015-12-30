package removeDupLetters;

import java.util.Arrays;
import java.util.Stack;

//Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
// You must make sure your result is the smallest in lexicographical order among all possible results.
//
//Example:
//Given "bcabc"
//Return "abc"
//
//Given "cbacdcbc"
//Return "acdb"
//

public class Solution {
	public static void main(String[] args) {
		System.out.println(new Solution().removeDuplicateLetters("bcabc"));
	}

	public String removeDuplicateLetters(String s) {
		boolean[] hit = new boolean[26];
		for (char c : s.toCharArray()) {
			hit[c - 'a'] = true;
		}
		int len = 0;
		for (boolean b : hit) {
			if (b) {
				len++;
			}
		}
		Arrays.fill(hit, false);
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int need = len - stack.size();
			while (!stack.isEmpty() && stack.peek() > c && !hit[c - 'a']
					&& s.length() - i >= need
					&& s.substring(i).contains("" + stack.peek())) {
				hit[stack.pop() - 'a'] = false;
			}
			if (!hit[c - 'a']) {
				hit[c - 'a'] = true;
				stack.push(c);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}
		return sb.toString();
	}
}
