package findTheCelebrity;

import java.util.Stack;

//Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, 
//there may exist one celebrity. 
//The definition of a celebrity is that all the other n - 1 people know him/her but 
//he/she does not know any of them.
//
//Now you want to find out who the celebrity is or verify that there is not one. 
//The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" 
//	to get information of whether A knows B. You need to find out the celebrity
//	(or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
//
//You are given a helper function bool knows(a, b) which tells you whether A knows B. 
//Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
//
//Note: There will be exactly one celebrity if he/she is in the party. 
//Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
public class Solution {
	public static void main(String[] args) {
		System.out.println(new Solution().findCelebrity(3));
	}

	// it's pretty trick to get right: when we eliminate elements, it's possible
	// that we elimite two elements together
	// thus left only one in stack and stop eliminating without ever check it
	// the last one left needs to be checked with all other elements such that
	// it doesn't know any one and every one knows it
	public int findCelebrity(int n) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			stack.push(i);
		}
		while (stack.size() > 1) {
			int a = stack.pop();
			int b = stack.pop();
			boolean aKnowsB = knows(a, b);
			boolean bKnowsA = knows(b, a);
			if (aKnowsB && !bKnowsA) {
				stack.push(b);
			}
			if (!aKnowsB && bKnowsA) {
				stack.push(a);
			}
		}
		if (stack.size() != 1)
			return -1;
		// there could only be one node left
		int ret = stack.pop();
		for (int i = 0; i < n; i++) {
			if (i != ret && (!knows(i, ret) || knows(ret, i))) {
				return -1;
			}
		}
		return ret;
	}

	boolean knows(int a, int b) {
		// boolean[][] ba = { { false, false, false, false, false, false, false
		// },
		// { true, false, true, false, false, false, true },
		// { true, true, false, false, false, true, false },
		// { true, false, false, false, true, false, true },
		// { true, true, true, false, false, true, true },
		// { true, false, true, false, true, false, false, },
		// { true, false, true, false, true, true, false } };
		boolean[][] ba = { { false, true, false }, { true, false, true },
				{ true, true, false } };

		return ba[a][b];
	}
}
