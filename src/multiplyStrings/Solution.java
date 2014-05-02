package multiplyStrings;

//Given two numbers represented as strings, return multiplication of the numbers as a string.
//
//Note: The numbers can be arbitrarily large and are non-negative.
public class Solution {
	public String multiply2(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0"))
			return "0";
		int len1 = num1.length();
		int len2 = num2.length();
		int[] rst = new int[len1 + len2 - 1];
		for (int ptr1 = 0; ptr1 < len1; ptr1++) {
			for (int ptr2 = 0; ptr2 < len2; ptr2++) {
				// starting position
				int cur = rst.length - (len2 - ptr2) - (len1 - 1 - ptr1);
				rst[cur] += (num1.charAt(ptr1) - '0')
						* (num2.charAt(ptr2) - '0');
			}
		}
		// straighten the number
		int carray = 0;
		for (int i = rst.length - 1; i >= 0; i--) {
			int tmpRst = rst[i] + carray;
			rst[i] = tmpRst % 10;
			if (tmpRst > 9) {
				carray = tmpRst / 10;
			} else {
				carray = 0;
			}
		}
		StringBuilder sb = new StringBuilder();
		if (carray > 0)
			sb.append(carray);
		for (int i : rst)
			sb.append(i);
		return sb.toString();
	}

	// an more intuitive solution: start from 0, first just add the numbers without considering carry, deal with carry later
	// i.e 32 * 18
	// get 3 26 16
	// convert it to 5 7 6
	public String multiplyNew(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0"))
			return "0";
		int len1 = num1.length();
		int len2 = num2.length();
		int[] rst = new int[len1 + len2];
		int[] n1 = new int[len1];
		int[] n2 = new int[len2];
		for (int i = 0; i < len1; i++) {
			n1[i] = num1.charAt(i) - '0';
		}
		for (int i = 0; i < len2; i++) {
			n2[i] = num2.charAt(i) - '0';
		}

		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				rst[i + j + 1] += n1[i] * n2[j];
			}
		}
		// 'contract' rst
		for (int i = len1 + len2 - 1; i >= 0; i--) {
			// note when i == 0 it can't be over 9, therefore we don't need to check i>0
			if (rst[i] > 9) {
				rst[i - 1] += rst[i] / 10;
			}
			rst[i] = rst[i] % 10;
		}
		StringBuilder sb = new StringBuilder();
		int start = 0;
		if (rst[0] == 0)
			start = 1;
		for (int i = start; i < rst.length; i++) {
			sb.append(rst[i]);
		}
		return sb.toString();

	}

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
		System.out.println(new Solution().multiply2("123", "28"));
		System.out.println(new Solution().multiply("123", "28"));
	}
}
