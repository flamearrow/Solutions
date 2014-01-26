package generateParens;

import java.util.ArrayList;

//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//For example, given n = 3, a solution set is:
//
//"((()))", "(()())", "(())()", "()(())", "()()()" 
public class Solution {
	// imitate a bSearch and check right > left
	ArrayList<String> ret = new ArrayList<String>();

	public ArrayList<String> generateParenthesis(int n) {
		doGenerate(n, n, "");
		return ret;
	}

	void doGenerate(int left, int right, String current) {
		if (left == 0 && right == 0) {
			ret.add(current);
			return;
		}
		if (left > 0)
			doGenerate(left - 1, right, current + "(");
		if (right > left)
			doGenerate(left, right - 1, current + ")");
	}

	public static void main(String[] args) {
		for (String s : new Solution().generateParenthesis(3)) {
			System.out.println(s);
		}
	}
}
