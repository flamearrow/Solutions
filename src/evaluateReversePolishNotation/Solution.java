package evaluateReversePolishNotation;

// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
// Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
// Some examples:
//  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
//  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
//  ["3","-4","+"]

public class Solution {
	public int evalRPN(String[] tokens) {
		int i = 0, j = 0;
		int a = 0, b = 0;
		String op = "";
		// need to change the array
		// time to terminate is when we reach the array end
		here: while (i < tokens.length) {
			// find the closest op
			while (tokens[i].matches("-*\\d+") || tokens[i].equals("~")) {
				i++;
				if (i == tokens.length)
					break here;
			}
			op = tokens[i];
			// find previous two valid digit, do the math
			j = i - 1;
			while (tokens[j].equals("~"))
				j--;
			b = Integer.parseInt(tokens[j]);
			tokens[j--] = "~";
			while (tokens[j].equals("~"))
				j--;
			a = Integer.parseInt(tokens[j]);
			tokens[j--] = "~";
			tokens[i] = doMath(a, b, op);
		}
		return Integer.parseInt(tokens[tokens.length - 1]);
	}

	String doMath(int a, int b, String op) {
		if (op.equals("+")) {
			return "" + (a + b);
		} else if (op.equals("-")) {
			return "" + (a - b);
		} else if (op.equals("*")) {
			return "" + (a * b);
		} else if (op.equals("/")) {
			return "" + (a / b);
		} else {
			return "~";
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] tokens = { "3", "-4", "+" };
		System.err.println(s.evalRPN(tokens));
	}
}
