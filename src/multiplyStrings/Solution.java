package multiplyStrings;

//Given two numbers represented as strings, return multiplication of the numbers as a string.
//
//Note: The numbers can be arbitrarily large and are non-negative.
public class Solution {

	// are they integer? - yes
	public String multiply(String num1, String num2) {
		int[] result = new int[num1.length() + num2.length()];
		int i1 = num1.length() - 1, i2 = num2.length() - 1;
		int tmpRst;
		while (i2 >= 0) {
			for (i1 = num1.length() - 1; i1 >= 0; i1--) {
				tmpRst = (num1.charAt(i1) - '0') * (num2.charAt(i2) - '0');
				int index = result.length - 1 - (num1.length() - 1 - i1)
						- (num2.length() - 1 - i2);
				addToRst(result, tmpRst, index);
			}
			i2--;
		}
		StringBuilder sb = new StringBuilder();
		int leadingZero = 0;
		while (leadingZero != result.length && result[leadingZero] == 0) {
			leadingZero++;
		}
		if (leadingZero == result.length) {
			return "0";
		}
		while (leadingZero < result.length) {
			sb.append(result[leadingZero]);
			leadingZero++;
		}
		return sb.toString();
	}

	void addToRst(int[] result, int tmpRst, int index) {
		int carry = tmpRst / 10;
		carry += (tmpRst % 10 + result[index]) / 10;
		result[index] = (tmpRst + result[index]) % 10;
		while (carry != 0) {
			index--;
			if ((result[index] + carry) >= 10) {
				result[index] = (result[index] + carry) % 10;
				carry = 1;
			} else {
				result[index] = (result[index] + carry) % 10;
				carry = 0;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().multiply("123123123123", "123123123123"));
	}
}
