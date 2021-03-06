package com.ml.gb.operators;

import java.util.Stack;

/**
 * takes a reverse polish expression as input, build a syntax tree
 * @author Chen
 *
 */
public class Expression implements Operator {
	private Operator syntaxTree = null;

	public Expression(String s) {
		Stack<Operator> stack = new Stack<Operator>();
		for (String token : s.split(" ")) {
			if (token.equals("+")) {
				stack.push(new Add(stack.pop(), stack.pop()));
			} else if (token.equals("-")) {
				Operator right = stack.pop();
				Operator left = stack.pop();
				stack.push(new Minus(left, right));
			} else if (token.equals("*")) {
				stack.push(new Multiply(stack.pop(), stack.pop()));
			} else if (token.equals("/")) {
				Operator right = stack.pop();
				Operator left = stack.pop();
				stack.push(new Divide(left, right));
			} else {
				stack.push(new Number(Integer.parseInt(token)));
			}
		}
		syntaxTree = stack.pop();
	}

	@Override
	public int operate() {
		return syntaxTree.operate();
	}

}
