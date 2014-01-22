package longestValidParentheses;

import java.util.Stack;

//Given a string containing just the characters '(' and ')', 
//find the length of the longest valid (well-formed) parentheses substring.
//
//For "(()", the longest valid parentheses substring is "()", which has length = 2.
//
//Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4. 
public class Solution {
	// use stack to store the ( positions, remember the last position of incorrect )
	public int longestValidParentheses(String s) {
		int last = -1;
		int ret = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				if (stack.empty()) {
					last = i;
				} else {
					stack.pop();
					if (stack.empty()) {
						if ((i - last) > ret) {
							ret = i - last;
						}
					} else {
						if (i - stack.peek() > ret) {
							ret = i - stack.peek();
						}
					}
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		String s = "(()()";
		System.out.println(new Solution().longestValidParentheses(s));
	}
}
