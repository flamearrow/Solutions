package sortTwoStacks;

import java.util.Stack;

/**
 * Sort a stack using another stack as tmp
 * @author flamearrow
 *
 */
public class Solution {
	// it's like insertion sort, each time we find the correct place of top of s1 tS1 in s2, push tS1 into s2
	// then all elements originally up tS1 in s2 will be pushed into tS1
	// keep doing this until tS1 becomes empty
	static void sortNew(Stack<Integer> s) {
		Stack<Integer> tStack = new Stack<Integer>();
		while (!s.isEmpty())
			tStack.push(s.pop());
		while (!tStack.isEmpty()) {
			int tmp = tStack.pop();
			while (!s.isEmpty() && s.peek() > tmp) {
				tStack.push(s.pop());
			}
			s.push(tmp);
		}
	}

	static void sort(Stack<Integer> s) {
		Stack<Integer> tStack = new Stack<Integer>();
		while (!s.isEmpty())
			tStack.push(s.pop());
		int tmp = Integer.MAX_VALUE;
		while (!tStack.empty()) {
			tmp = Integer.MAX_VALUE;
			while (!tStack.empty()) {
				int next = tStack.pop();
				if (next < tmp) {
					tmp = next;
				}
				s.push(next);
			}

			while (!s.isEmpty() && s.peek() >= tmp) {
				int next = s.pop();
				if (next != tmp)
					tStack.push(next);
			}
			s.push(tmp);
		}
	}

	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(3);
		s.push(5);
		s.push(2);
		sort(s);
		while (!s.isEmpty()) {
			System.out.println(s.pop());
		}

	}
}
