package evaluateExpr;

//Given a sequence
//3 + 4 * 5 * 6 + 3 + 7 + ...
//of single digits, + and *
//Evaluate it.
public class Solution {
	// the idea is to keep track of current running product and sum
	// only update sum when we meet a non digit
	int evaluate(String expr) {
		int product = 0;
		int sum = 0;
		int pre = 0;
		for (char c : expr.toCharArray()) {
			if (c == '*') {
				if (product == 0)
					product = pre;
				// otherwise we're already multiplying
			} else if (c == '+') {
				// finished a multiply, add to sum
				if (product > 0) {
					sum += product;
					product = 0;
				}
				// otherwise we are adding, add to sum
				else {
					sum += pre;
				}
			} else {
				pre = c - '0';
				// we are multiplying, need to multiply to product
				if (product > 0) {
					product *= (int) (c - '0');
				}
			}
		}
		// finished with multiplication
		if (product > 0)
			sum += product;
		// finished with adding
		else
			sum += pre;

		return sum;
	}

	public static void main(String[] args) {
		String expr = "1*2*3*4+3*2";
		System.out.println(new Solution().evaluate(expr));
	}
}
