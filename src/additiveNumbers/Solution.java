package additiveNumbers;

//Additive number is a string whose digits can form additive sequence.
//
//A valid additive sequence should contain at least three numbers. 
// Except for the first two numbers, each subsequent number in the sequence
// must be the sum of the preceding two.
//
//For example:
//"112358" is an additive number because the digits can form an 
// additive sequence: 1, 1, 2, 3, 5, 8.
//
//1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
//"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
//1 + 99 = 100, 99 + 100 = 199
//Note: Numbers in the additive sequence cannot have leading zeros, so sequence
// 1, 2, 03 or 1, 02, 3 is invalid.
//
//Given a string containing only digits '0'-'9', write a function to determine 
// if it's an additive number.
public class Solution {
	public static void main(String[] args) {
		String num = "101";
		System.out.println(new Solution().isAdditiveNumber(num));
	}
	// so the idea is try to split result and right
	// if we pick one split, we don't need to futher split, because all numbers would chain together automatically
	//  to chain it, write something to accept a substring, a right int and a result int
	//  find if substirng is null or substring contains (result-right) as its tail and continue probe until we're fucked
	//  or substirng drains to 0 length
	public boolean isAdditiveNumber(String num) {
		for (int i = num.length() - 2; i > 0; i--) {
			String rightPair = num.substring(i);
			for (int j = 1; j < rightPair.length(); j++) {
				String leftStr = rightPair.substring(0, j);
				String rightStr = rightPair.substring(j);
				if (leftStr.length() > 1 && leftStr.startsWith("0")
						|| rightStr.length() > 1 && rightStr.startsWith("0")) {
					continue;
				}
				long left = Long.parseLong(leftStr);
				long right = Long.parseLong(rightStr);
				if (doProbe(num.substring(0, i), left, right)) {
					return true;
				}
			}
		}
		return false;

	}

	boolean doProbe(String s, long right, long result) {
		if (s.length() == 0) {
			return true;
		}
		String left = "" + (result - right);
		if (s.endsWith(left)) {
			return doProbe(s.substring(0, s.length() - left.length()), Integer.parseInt(left), right);
		} else {
			return false;
		}
	}
}
