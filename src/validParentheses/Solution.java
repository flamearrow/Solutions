package validParentheses;

import java.util.LinkedList;
import java.util.Stack;

//Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
// determine if the input string is valid.
//
//The brackets must close in the correct order, "()" and "()[]{}" are all 
// valid but "(]" and "([)]" are not.
public class Solution {

	public boolean isValid2(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (left(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty())
					return false;
				else {
					char left = stack.pop();
					if (!matches(left, c))
						return false;
				}
			}
		}
		return stack.isEmpty();
	}

	boolean left(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	boolean matches(char left, char right) {
		return left == '(' && right == ')' || left == '[' && right == ']'
				|| left == '{' && right == '}';
	}

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
