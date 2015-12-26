package minStack;

import java.util.Stack;

//Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//push(x) -- Push element x onto stack.
//pop() -- Removes the element on top of the stack.
//top() -- Get the top element.
//getMin() -- Retrieve the minimum element in the stack.
class MinStack {
	Stack<Node> stack;

	public MinStack() {
		stack = new Stack<>();
	}

	public void push(int x) {
		int min = stack.isEmpty() ? x : stack.peek().min < x ? stack.peek().min
				: x;
		stack.push(new Node(x, min));
	}

	public void pop() {
		stack.pop();
	}

	public int top() {
		return stack.peek().val;
	}

	public int getMin() {
		return stack.peek().min;
	}
}

class Node {
	int val, min;

	public Node(int vVal, int vMin) {
		val = vVal;
		min = vMin;
	}
}
