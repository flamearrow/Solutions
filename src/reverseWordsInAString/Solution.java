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
	// cheating
	public String reverseWords2(String s) {
		// again, in regex
		//     +: one or more
		//     *: none or more
		//     ?: none or one
		String[] ss = s.trim().split(" +");
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String sss : ss) {
			if (first) {
				sb.insert(0, sss);
				first = false;
			} else {
				sb.insert(0, ' ');
				sb.insert(0, sss);
			}
		}
		return sb.toString();
	}

	public String reverseWords4(String s) {
		Stack<String> stack = new Stack<String>();
		int cur = 0;
		String next = null;
		while (true) {
			cur += skipSpace(s, cur);
			if (cur >= s.length())
				break;
			next = getNextWord(s, cur);
			stack.push(next);
			cur += next.length();
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while (!stack.isEmpty()) {
			if (first) {
				sb.append(stack.pop());
				first = false;
			} else {
				sb.append(' ');
				sb.append(stack.pop());
			}
		}
		return sb.toString();
	}

	private int skipSpace(String s, int cur) {
		int ret = 0;
		while (cur < s.length() && s.charAt(cur) == ' ') {
			cur++;
			ret++;
		}
		return ret;
	}

	private String getNextWord(String s, int cur) {
		int prob = cur;
		while (prob < s.length() && s.charAt(prob) != ' ') {
			prob++;
		}
		return s.substring(cur, prob);
	}
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
