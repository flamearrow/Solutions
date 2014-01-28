package validParentheses;

import java.util.LinkedList;

//Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
// determine if the input string is valid.
//
//The brackets must close in the correct order, "()" and "()[]{}" are all 
// valid but "(]" and "([)]" are not.
public class Solution {
	// (): parentheses
	// []: brackets
	// {}: braces or curly brackets
	public boolean isValid(String s) {
		LinkedList<Character> stack = new LinkedList<Character>();

		for (char c : s.toCharArray()) {
			if (c == ')' || c == ']' || c == '}') {
				if (stack.isEmpty() || stack.removeLast() != getLeft(c))
					return false;
			} else
				stack.add(c);
		}
		return stack.isEmpty();
	}

	char getLeft(char c) {
		switch (c) {
		case ')':
			return '(';
		case ']':
			return '[';
		case '}':
			return '{';
		default:
			return 0;
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().isValid("(()([)[]{}"));
	}
}
