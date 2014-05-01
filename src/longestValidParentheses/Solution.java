package longestValidParentheses;

import java.util.Stack;

//Given a string containing just the characters '(' and ')', 
//find the length of the longest valid (well-formed) parentheses substring.
//
//For "(()", the longest valid parentheses substring is "()", which has length = 2.
//
//Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4. 
public class Solution {
	public int longestValidParentheses(String s) {
		// use stack to store positions, remember the last wild ')'
		int prevPosition = -1;
		int ret = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				// wild ')', remember its location
				if (stack.isEmpty()) {
					prevPosition = i;
				} else {
					stack.pop();
					if (stack.isEmpty()) {
						if (i - prevPosition > ret)
							ret = i - prevPosition;
					} else {
						if (i - stack.peek() > ret)
							ret = i - stack.peek();
					}
				}
			}
		}
		return ret;
	}

	// use stack to store the ( positions, remember the last position of incorrect )
	public int longestValidParenthesesNew(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int last = -1;
		int ret = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				// found a wild ), this one won't be valid, mark it
				if (stack.isEmpty()) {
					last = i;
				} else {
					stack.pop();
					// we have finished a bunch of matched pair
					// check if there is unmatched prior to that
					if (stack.isEmpty()) {
						if (i - last > ret) {
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
		String s = "()(()";
		System.out.println(new Solution().longestValidParenthesesNew(s));
	}
}
